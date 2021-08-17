/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.gui.MapDisplayerTopComponent;
import java.beans.IntrospectionException;
import java.util.Set;
import javax.swing.Action;
import org.openide.actions.OpenAction;
import org.openide.awt.Actions;
import org.openide.cookies.OpenCookie;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapNode extends BeanNode<MapBean> {

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

        this.mapBean = mapBean;
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");

    }

    @Override
    public String getHtmlDisplayName() {
        if (mapBean != null) {
            if (mapBean.isActive()) {

                return "<b>" + mapBean.getName() + "</b>";
            } else {
                return mapBean.getName();
            }
        }
        return super.getDisplayName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Action getPreferredAction() {
        return SystemAction.get(OpenAction.class);
    }

    @Override
    public Action[] getActions(boolean context) {

        return new Action[]{
            SystemAction.get(OpenAction.class),
            Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.ActivateMap"),
            Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.AddLayer"),
            Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.MapProperties")
        };
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
    
}
