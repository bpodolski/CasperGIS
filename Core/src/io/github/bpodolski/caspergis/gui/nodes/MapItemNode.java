/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.beans.MapElementFlavor;
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
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.ExTransferable;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapItemNode extends BeanNode<MapElementBean> {

    private MapItemsFactory factory;
    private MapElementBean bean;
//    private InstanceContent instContent;

    public MapItemNode(MapElementBean bean, Children children, Lookup lookup, MapItemsFactory mapItemsFactory) throws IntrospectionException {
        super(bean, children, lookup);
        this.factory = mapItemsFactory;
        this.bean = bean;
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
//        factory.removeChild(bean);
//        fireNodeDestroyed();
        factory.removeChild(getLookup().lookup(MapElementBean.class));
    }

    @Override
    public Transferable clipboardCut() throws IOException {
        Transferable deflt = super.clipboardCut();
        ExTransferable added = ExTransferable.create(deflt);
        added.put(new ExTransferable.Single(MapElementFlavor.MAPELEMENT_FLAVOR) {
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
        added.put(new ExTransferable.Single(MapElementFlavor.MAPELEMENT_FLAVOR) {
            @Override
            protected MapElementBean getData() {
                return getLookup().lookup(MapElementBean.class);
            }
        });
        return added;
    }
}
