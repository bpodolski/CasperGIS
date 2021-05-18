/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapItemsFactory;
import io.github.bpodolski.caspergis.gui.nodes.factories.ProjectItemsFactory;
import java.beans.IntrospectionException;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class InternalMapNode extends BeanNode<MapBean> {

    public InternalMapNode(MapBean bean) throws IntrospectionException {
        super(bean, Children.create(new MapItemsFactory(bean), true), Lookups.singleton(bean));
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");

    }

    @Override
    public Action getPreferredAction() {
        return null;
    }
}
