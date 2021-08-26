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
import java.util.HashMap;
import javax.swing.event.ChangeEvent;
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
public class CoreMapExplorerManagerMgr extends MapExplorerManagerMgr implements ChangeListener {

    private final HashMap mapExplorerManagers = new HashMap<MapBean, ExplorerManager>();
    private final ExplorerManager defaultMgr = new ExplorerManager();
    private MapBean activeMapBean;
    private final MapBean defaultMapBean = new MapBean(null, "[No active map]");

    private final ChangeSupport cs = new ChangeSupport(this);

    public CoreMapExplorerManagerMgr() {
        Node rootNode;
        try {
            rootNode = new BeanNode(defaultMapBean);
            rootNode.setName("[No active map]");
            defaultMgr.setRootContext(rootNode);
            mapExplorerManagers.put(defaultMapBean, defaultMgr);
            
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void addMapExplorerManager(MapBean mapBean, ExplorerManager mgr) {
        mapExplorerManagers.put(mapBean, mgr);
        mapBean.addChangeListener(this);

    }

    @Override
    public void addMapExplorerManager(MapBean mapBean) {
        ExplorerManager mgr = new ExplorerManager();
        try {
            // TODO test default add node to mgr
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
        setActiveMapBean(null);
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
    public void stateChanged(ChangeEvent e) {
        MapBean mapBean = (MapBean) e.getSource();
        if (this.getActiveMapBean() != mapBean) {
            if (mapBean.isActive()) {
                setActiveMapBean(mapBean);
            } else {

            }
        } else {
            if (!mapBean.isActive()) {
                clearActiveMapBean();
            } else {

            }
        }
    }

}
