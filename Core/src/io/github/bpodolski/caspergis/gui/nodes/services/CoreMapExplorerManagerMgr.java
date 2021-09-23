/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.gui.nodes.InternalMapNode;
import io.github.bpodolski.caspergis.services.MapExplorerManagerMgr;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javax.swing.event.ChangeListener;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Node;
import org.openide.util.ChangeSupport;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = MapExplorerManagerMgr.class, path = "Core")
public final class CoreMapExplorerManagerMgr extends MapExplorerManagerMgr implements PropertyChangeListener {

    private final HashMap mapExplorerManagers = new HashMap<MapBean, ExplorerManager>();

    private final ExplorerManager defaultMgr = new ExplorerManager();

    private MapBean activeMapBean;
    private final MapBean defaultMapBean = new MapBean(null, "[No active map]");

    private final ChangeSupport cs = new ChangeSupport(this);

    public CoreMapExplorerManagerMgr() {
        Node rootNode;
        defaultMapBean.setActive(false);
        try {
            rootNode = new BeanNode(defaultMapBean);
            rootNode.setName("[No active map]");
            defaultMgr.setRootContext(rootNode);
            mapExplorerManagers.put(defaultMapBean, defaultMgr);

            clearActiveMapBean();

        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void addMapExplorerManager(MapBean mapBean, ExplorerManager mgr) {
        if (mapExplorerManagers.get(mapBean) == null) {
            mapExplorerManagers.put(mapBean, mgr);
            mapBean.addPropertyChangeListener(this);
        }
    }

    @Override
    public void addMapExplorerManager(MapBean mapBean) {
        ExplorerManager mgr = new ExplorerManager();
        try {
            // TODO test default add node to mgr
//            BeanNode node = new BeanNode(mapBean);
            InternalMapNode node = new InternalMapNode(mapBean);
            mgr.setRootContext(node);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        this.addMapExplorerManager(mapBean, mgr);
    }

    @Override
    public ExplorerManager getMapExplorerManager(MapBean mapBean) {
        ExplorerManager m = (ExplorerManager) mapExplorerManagers.get(mapBean);
        if (m != null) {
            return m;
        }
        return this.defaultMgr;
    }

    @Override
    public void deleteMapExplorerManager(MapBean mapBean) {
        mapExplorerManagers.remove(mapBean);
    }

    @Override
    public MapBean getActiveMapBean() {
        return activeMapBean;
    }

    @Override
    public void setActiveMapBean(MapBean activeMapBean) {
        this.activeMapBean = activeMapBean;
        cs.fireChange();
    }

    @Override
    public void clearActiveMapBean() {
        setActiveMapBean(defaultMapBean);
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("active")) {
            MapBean mapBean = (MapBean) evt.getSource();
            if (evt.getNewValue().equals(Boolean.FALSE)) {
                clearActiveMapBean();
            } else {
                setActiveMapBean(mapBean);
            }
        }

    }

}
