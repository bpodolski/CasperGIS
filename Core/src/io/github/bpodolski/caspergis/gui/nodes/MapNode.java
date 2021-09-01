/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.gui.MapDisplayerTopComponent;
import java.awt.Image;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import javax.swing.Action;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.actions.OpenAction;
import org.openide.awt.Actions;
import org.openide.cookies.OpenCookie;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapNode extends BeanNode<MapBean> implements PropertyChangeListener {

    MapBean mapBean;

    public MapNode(MapBean mapBean) throws IntrospectionException {
        this(mapBean, new InstanceContent());
    }

    public MapNode(MapBean mapBean, InstanceContent ic) throws IntrospectionException {
        super(mapBean, Children.LEAF, new AbstractLookup(ic));
        ic.add((OpenCookie) () -> {
            TopComponent tc = (TopComponent) findTopComponent(mapBean);
            if (tc == null) {
                tc = new MapDisplayerTopComponent(mapBean);
                tc.open();
            }

            tc.requestActive();
        });

        ic.add(mapBean);

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
//                model.reorder(perm);
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

        if (this.mapBean.isActive()) {
            return getActiveMapAction();
        } else {
            return getInactiveMapAction();
        }
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

    private Action[] getActiveMapAction() {
        Action ac = Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.DeactivateMap");
        return new Action[]{
            SystemAction.get(OpenAction.class),
            ac,
            Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.AddLayer"),
            Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.MapProperties")
        };
    }

    private Action[] getInactiveMapAction() {
        Action ac = Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.ActivateMap");
        return new Action[]{
            SystemAction.get(OpenAction.class),
            ac,
            Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.MapProperties")
        };
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

    
}
