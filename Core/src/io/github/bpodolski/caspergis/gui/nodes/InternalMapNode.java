/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ElementMapBean;
import io.github.bpodolski.caspergis.beans.ElementMapFlavor;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapItemsFactory;
import io.github.bpodolski.caspergis.utils.LayerFileFilter;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.PasteAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.Exceptions;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class InternalMapNode extends BeanNode<MapBean> {
    
    private MapItemsFactory factory;
    private MapBean bean;
    private InstanceContent instContent;
    
    public InternalMapNode(MapBean bean) throws IntrospectionException {
        this(bean, new MapItemsFactory(bean), new InstanceContent());
    }
    
    public InternalMapNode(MapBean bean, final MapItemsFactory factory, InstanceContent instContent) throws IntrospectionException {
        super(bean, Children.create(factory, true), new ProxyLookup(Lookups.singleton(bean), new AbstractLookup(instContent)));
        
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");
        
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
            PasteAction.get(PasteAction.class),};
    }
    
    @Override
    protected void createPasteTypes(Transferable t, List<PasteType> s) {
        super.createPasteTypes(t, s);
        PasteType p = getDropType(t, 0, 0);
        if (p != null) {
            s.add(p);
        }
    }
    
    @Override
    public PasteType getDropType(final Transferable t, int arg1, int arg2) {
        if (t.isDataFlavorSupported(ElementMapFlavor.MAPELEMENT_FLAVOR)) {
            return new PasteType() {
                @Override
                public Transferable paste() throws IOException {
                    try {
                        factory.add((ElementMapBean) t.getTransferData(ElementMapFlavor.MAPELEMENT_FLAVOR));
                        final Node node = NodeTransfer.node(t, NodeTransfer.DND_MOVE + NodeTransfer.CLIPBOARD_CUT);
//                        if (node != null) {
//                            node.destroy();
//                        }
                    } catch (UnsupportedFlavorException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    return null;
                }
            };
        } else if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            return new PasteType() {
                @Override
                public Transferable paste() throws IOException {
                    try {
                        List fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
                        for (Object f : fileList) {
                            File file = (File) f;
                            
                            if (LayerFileFilter.LAYER_FILEFILTER.accept(file)) {
                                LayerBean lb = new LayerBean(file.getName());
                                lb.setConnectionStr(file.getAbsolutePath());
                                factory.add(lb);
                            }
                        }
                    } catch (UnsupportedFlavorException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    return null;
                }
            };
            
        } else {
            return null;
        }
    }

    public MapItemsFactory getFactory() {
        return factory;
    }
    
    
}
