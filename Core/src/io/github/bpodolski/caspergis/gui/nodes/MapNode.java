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

    public MapNode(MapBean mapBean) throws IntrospectionException {
        this(mapBean, new InstanceContent());
    }

    public MapNode(MapBean mapBean, InstanceContent ic) throws IntrospectionException {
        super(mapBean, Children.LEAF, new AbstractLookup(ic));
        ic.add(new OpenCookie() {
            @Override
            public void open() {
                TopComponent tc = findTopComponent(mapBean);
                if (tc == null) {
                    tc = new MapDisplayerTopComponent(mapBean);
                    tc.open();
                }
                tc.requestActive();
            }
        });
        setDisplayName(mapBean.getDisplayName());
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");

    }

    @Override
    public Action getPreferredAction() {
        return SystemAction.get(OpenAction.class);
    }

    private TopComponent findTopComponent(MapBean mapBean) {
        Set<TopComponent> openTopComponents = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : openTopComponents) {
            if (tc.getLookup().lookup(MapBean.class) == mapBean) {
                return tc;
            }
        }
        return null;
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{SystemAction.get(OpenAction.class)};
    }
}
