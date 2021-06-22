/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui;

import io.github.bpodolski.caspergis.CgRegistry;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapBeanEnv;
import io.github.bpodolski.caspergis.gui.geotools.JMapPanelCG;
import io.github.bpodolski.caspergis.gui.nodes.InternalMapNode;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ActionMap;
import org.geotools.map.MapContent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.PasteAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//io.github.bpodolski.caspergis.gui//ActiveMapEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ActiveMapEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "io.github.bpodolski.caspergis.gui.ActiveMapEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ActiveMapEditorAction",
        preferredID = "ActiveMapEditorTopComponent"
)
@Messages({
    "CTL_ActiveMapEditorAction=ActiveMapEditor",
    "CTL_ActiveMapEditorTopComponent=ActiveMapEditor Window",
    "HINT_ActiveMapEditorTopComponent=This is a ActiveMapEditor window"
})
public final class ActiveMapEditorTopComponent extends TopComponent implements ExplorerManager.Provider, PropertyChangeListener {

    private final ExplorerManager mgr = new ExplorerManager();
    MapBean mapBean = new MapBean(null, "Layers");
    MapBeanEnv regMapBean = null;

    InstanceContent instanceContent = new InstanceContent();

    Lookup lookupMapBean = null;
    Lookup lookupAction = null;
    ProxyLookup proxyLookup;

    LayerListTopComponent layerListTC = null;

    JMapPanelCG mapPanel = new JMapPanelCG();

    public ActiveMapEditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_ActiveMapEditorTopComponent());
        setToolTipText(Bundle.HINT_ActiveMapEditorTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);

        regMapBean = new MapBeanEnv(this.mapBean, true);

        initView();
        initActions();

        CgRegistry.topComponentMap.remove(mapBean);
        CgRegistry.topComponentMap.put(mapBean, this);

        mgr.addPropertyChangeListener(this);

        lookupMapBean = new AbstractLookup(this.instanceContent);
        instanceContent.add(regMapBean);
        proxyLookup = new ProxyLookup(lookupAction, lookupMapBean);

        associateLookup(this.proxyLookup);

        findLayerListTC();
        if (layerListTC != null) {
            layerListTC.setExplorerManager(mgr);
        }

// Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");

        this.pnlMap.add(this.mapPanel, java.awt.BorderLayout.CENTER);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        testBtn = new javax.swing.JButton();
        view = new org.openide.explorer.view.BeanTreeView();
        pnlMap = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();

        org.openide.awt.Mnemonics.setLocalizedText(testBtn, org.openide.util.NbBundle.getMessage(ActiveMapEditorTopComponent.class, "ActiveMapEditorTopComponent.testBtn.text")); // NOI18N
        testBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testBtnActionPerformed(evt);
            }
        });

        view.setRootVisible(false);

        pnlMap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMap.setLayout(new java.awt.BorderLayout());

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        pnlMap.add(jScrollPane1, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(testBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void testBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testBtnActionPerformed

    }//GEN-LAST:event_testBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlMap;
    private javax.swing.JButton testBtn;
    private javax.swing.JTextArea txt;
    private org.openide.explorer.view.BeanTreeView view;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    private void initView() {
        Node rootNode = null;
        try {
            rootNode = new InternalMapNode(this.mapBean);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        mgr.setRootContext(rootNode);
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    private void initActions() {
        ActionMap map = this.getActionMap();

        CutAction cut = SystemAction.get(CutAction.class);
        map.put(cut.getActionMapKey(), ExplorerUtils.actionCut(mgr));

        CopyAction copy = SystemAction.get(CopyAction.class);
        map.put(copy.getActionMapKey(), ExplorerUtils.actionCopy(mgr));

        PasteAction paste = SystemAction.get(PasteAction.class);
        map.put(paste.getActionMapKey(), ExplorerUtils.actionPaste(mgr));

        DeleteAction delete = SystemAction.get(DeleteAction.class);
        map.put(delete.getActionMapKey(), ExplorerUtils.actionDelete(mgr, true));


        this.lookupAction = ExplorerUtils.createLookup(mgr, map);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals("selectedNodes")) {
//            Node[] selectedNode = (Node[]) evt.getNewValue();
//            for (Node node : selectedNode) {
//                io.github.bpodolski.caspergis.utils.CgUtils.io.getOut().println("node: " + node.getDisplayName());
//            }
//        }
    }

    private void findLayerListTC() {
        layerListTC = (LayerListTopComponent) WindowManager.getDefault().findTopComponent("LayerListTopComponent");

    }
}
