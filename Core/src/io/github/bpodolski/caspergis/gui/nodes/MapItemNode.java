/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapItemsFactory;
import java.beans.IntrospectionException;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapItemNode extends BeanNode<MapElementBean> {

    private MapItemsFactory factory;
    private MapElementBean bean;
    private InstanceContent instContent;

    public MapItemNode(MapElementBean bean, final MapItemsFactory factory) throws IntrospectionException {
        this(bean, factory, new InstanceContent());
    }

    public MapItemNode(MapElementBean bean, final MapItemsFactory factory, InstanceContent instContent) throws IntrospectionException {
        super(bean, Children.create(factory, true), new ProxyLookup(Lookups.singleton(bean), new AbstractLookup(instContent)));
        this.bean = bean;
        this.factory = factory;
        this.setDisplayName(bean.getName());
        this.instContent = instContent;

        instContent.add(new Index.Support() {

            @Override
            public Node[] getNodes() {
                return getChildren().getNodes();
            }

            @Override
            public int getNodesCount() {
                return getNodes().length;
            }

            @Override
            public void reorder(int[] perm) {
                factory.reorder(perm);

            }
        });

    }

    @Override
    public Action getPreferredAction() {
        return null;
    }

}
