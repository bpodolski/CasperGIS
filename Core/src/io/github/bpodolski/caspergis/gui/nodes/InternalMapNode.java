/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.beans.MapitemFlavor;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapitemsFactory;
import io.github.bpodolski.caspergis.utils.CgUtils;
import io.github.bpodolski.caspergis.utils.LayerFileFilter;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.OpenAction;
import org.openide.actions.PasteAction;
import org.openide.awt.Actions;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class InternalMapNode extends BeanNode<MapBean> implements PropertyChangeListener {

    private MapitemsFactory factory;
    private MapBean mapBean;
    private InstanceContent instContent;

    public InternalMapNode(MapBean mapBean) throws IntrospectionException {
        this(mapBean, new MapitemsFactory(mapBean), new InstanceContent());
    }

    public InternalMapNode(MapBean mapBean, final MapitemsFactory factory, InstanceContent instContent) throws IntrospectionException {
        super(mapBean, Children.create(factory, true), new ProxyLookup(Lookups.singleton(mapBean), new AbstractLookup(instContent)));

         instContent.add(new Index.Support() {
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
         
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");
        this.instContent = instContent;

        this.mapBean = mapBean;
        this.setDisplayName(mapBean.getName());
        this.instContent = instContent;

        instContent.add(this);

        this.mapBean = mapBean;
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");
        mapBean.addPropertyChangeListener(this);
        
        this.factory = factory;
    }

    @Override
    public Action getPreferredAction() {
        return Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.MapProperties");
    }

    @Override
    public Action[] getActions(boolean context) {
        ArrayList actList = new ArrayList();

        if (this.mapBean.isActive()) {
            actList.add(SystemAction.get(OpenAction.class));
            actList.add(SystemAction.get(PasteAction.class));
            actList.add(Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.AddLayer"));
            actList.add(Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.MapProperties"));

        } else {
            return null;
        }

        return (Action[]) actList.toArray(new Action[actList.size()]);

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
         CgUtils.io.getOut().println("InternalMapNode.getDropType = 0" );
        if (this.mapBean.isActive()) {
            if (t.isDataFlavorSupported(MapitemFlavor.MAPELEMENT_FLAVOR)) {
             
                return new PasteType() {
                    @Override
                    public Transferable paste() throws IOException {
                        try {
                            factory.getModel().add((MapitemBean) t.getTransferData(MapitemFlavor.MAPELEMENT_FLAVOR));
                            final Node node = NodeTransfer.node(t, NodeTransfer.DND_MOVE + NodeTransfer.CLIPBOARD_CUT);
                            if (node != null) {
                                node.destroy();
                            }
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
                                    factory.getModel().add(lb);
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
        return null;
    }

    public MapitemsFactory getFactory() {
        return factory;
    }

    @Override
    public MapBean getBean() {
        return mapBean;
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

}
