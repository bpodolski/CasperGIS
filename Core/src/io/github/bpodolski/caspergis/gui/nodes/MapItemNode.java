/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapItemsFactory;
import java.awt.datatransfer.Transferable;
import java.beans.IntrospectionException;
import java.io.IOException;
import javax.swing.Action;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.MoveDownAction;
import org.openide.actions.MoveUpAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.ExTransferable;
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
        super(bean, null, new ProxyLookup(Lookups.singleton(bean), new AbstractLookup(instContent)));
        //        Children.create(factory, true)
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

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{
            CutAction.get(CutAction.class),
            CopyAction.get(CopyAction.class),
            DeleteAction.get(DeleteAction.class),
            SystemAction.get(MoveUpAction.class),
            SystemAction.get(MoveDownAction.class)
        };
    }

    @Override
    public boolean canCut() {
        return true;
    }

    @Override
    public boolean canCopy() {
        return true;
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        factory.removeChild(bean);
        fireNodeDestroyed();
    }

    //add Drag support
    @Override
    public Transferable drag() throws IOException {
        return bean;
    }

    @Override
    public Transferable clipboardCut() throws IOException {
        Transferable deflt = super.clipboardCut();
        ExTransferable added = ExTransferable.create(deflt);
        added.put(new ExTransferable.Single(MapElementBean.MAPELEMENT_FLAVOR) {
            @Override
            protected MapElementBean getData() {
                return getLookup().lookup(MapElementBean.class);
            }
        });
        return added;
    }

    @Override
    public Transferable clipboardCopy() throws IOException {
        Transferable deflt = super.clipboardCut();
        ExTransferable added = ExTransferable.create(deflt);
        added.put(new ExTransferable.Single(MapElementBean.MAPELEMENT_FLAVOR) {
            @Override
            protected MapElementBean getData() {
                return getLookup().lookup(MapElementBean.class);
            }
        });
        return added;
    }
}
