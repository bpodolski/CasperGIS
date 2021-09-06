/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.gui.MapDisplayerTopComponent;
import io.github.bpodolski.caspergis.gui.nodes.factories.ProjectItemsFactory;
import java.awt.Image;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.Action;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.actions.MoveDownAction;
import org.openide.actions.MoveUpAction;
import org.openide.actions.OpenAction;
import org.openide.actions.RenameAction;
import org.openide.actions.ReorderAction;
import org.openide.awt.Actions;
import org.openide.cookies.OpenCookie;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapNode extends BeanNode<MapBean> implements PropertyChangeListener {

    MapBean mapBean;
    ProjectItemsFactory factory;

    public MapNode(MapBean mapBean) throws IntrospectionException {
        this(mapBean, new InstanceContent());
    }

    public MapNode(MapBean mapBean, InstanceContent ic) throws IntrospectionException {
        super(mapBean, Children.LEAF, new ProxyLookup(new AbstractLookup(ic), Lookups.singleton(mapBean)));
        ic.add((OpenCookie) () -> {
            TopComponent tc = (TopComponent) findTopComponent(mapBean);
            if (tc == null) {
                tc = new MapDisplayerTopComponent(mapBean);
                tc.open();
            }

            tc.requestActive();
        });

        ic.add(new Index.Support() {
            @Override
            public Node[] getNodes() {
                return getChildren().getNodes(true);
            }

            @Override
            public int getNodesCount() {
                return getNodes().length;
            }

            @Override
            public void reorder(int[] perm) {
                factory.getModel().reorder(perm);
            }
        });

        this.mapBean = mapBean;
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");
        mapBean.addPropertyChangeListener(this);

    }

    @Override
    public Action getPreferredAction() {
        return SystemAction.get(OpenAction.class);
    }

    @Override
    public Action[] getActions(boolean context) {
        ArrayList actList = new ArrayList();

        MoveUpAction moveUpAction = SystemAction.get(MoveUpAction.class);
        MoveDownAction moveDownAction = SystemAction.get(MoveDownAction.class);
        RenameAction renameAction = SystemAction.get(RenameAction.class);

        actList.add(moveUpAction);
        actList.add(moveDownAction);
        actList.add(renameAction);

        if (this.mapBean.isActive()) {
            actList.add(SystemAction.get(OpenAction.class));
            actList.add(Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.DeactivateMap"));
            actList.add(Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.AddLayer"));
            actList.add(Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.MapProperties"));

        } else {
            actList.add(SystemAction.get(OpenAction.class));
            actList.add(Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.ActivateMap"));
            actList.add(Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.MapProperties"));
        }

        return (Action[]) actList.toArray(new Action[actList.size()]);
    }

    public TopComponent findTopComponent(MapBean mapBean) {
        Set<TopComponent> openTopComponents = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : openTopComponents) {
            if (tc.getLookup().lookup(MapBean.class
            ) == mapBean) {//
                if (tc.getClass().getSimpleName().equalsIgnoreCase("MapDisplayerTopComponent")) {
                    return tc;
                }
            }
        }
        return null;
    }

    @Override
    public Image getIcon(int type) {
        if (mapBean != null) {
            if (mapBean.isActive()) {
                return ImageUtilities.loadImage("io/github/bpodolski/caspergis/res/mapActive.png");
            }
        }
        return ImageUtilities.loadImage("io/github/bpodolski/caspergis/res/map.png");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (this.mapBean.isActive()) {
            setIconBaseWithExtension("io/github/bpodolski/caspergis/res/mapActive.png");
        } else {
            setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");
        }

        this.fireIconChange();
    }

    @Override
    public String getName() {
        MapBean bean = getLookup().lookup(MapBean.class);
        if (null != bean.getName()) {
            return bean.getName();
        }
        return super.getDisplayName();
    }

    @Override
    public void setName(String newDisplayName) {
        MapBean bean = getLookup().lookup(MapBean.class);
        String oldDisplayName = bean.getName();
        bean.setName(newDisplayName);
        fireNameChange(oldDisplayName, newDisplayName);
    }

    @Override
    public boolean canRename() {
        return true;
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        fireNodeDestroyed();
    }

    public ProjectItemsFactory getFactory() {
        return factory;
    }

    public void setFactory(ProjectItemsFactory factory) {
        this.factory = factory;
    }

}
