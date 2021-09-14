/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.gui.nodes.InternalMapNode;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapItemsFactory;
import io.github.bpodolski.caspergis.services.MapExplorerManagerMgr;
import io.github.bpodolski.caspergis.utils.LayerFileFilter;
import java.io.File;
import java.util.Collection;
import javax.swing.ActionMap;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.PasteAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//io.github.bpodolski.caspergis.gui//LayerList//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "LayerListTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "io.github.bpodolski.caspergis.gui.LayerListTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_LayerListAction",
        preferredID = "LayerListTopComponent"
)
@Messages({
    "CTL_LayerListAction=LayerList",
    "CTL_LayerListTopComponent=LayerList Window",
    "HINT_LayerListTopComponent=This is a LayerList window"
})
public final class LayerListTopComponent extends TopComponent implements ExplorerManager.Provider,
        /*LookupListener,*/ ChangeListener {

    private MapBean mapBean = null;
    private final MapBean mapBeanX = new MapBean(null, "[No active map]");

    //Serwis 
    MapExplorerManagerMgr explorerManagerMgr;

    Lookup lookupMapBean = null;
    Lookup lookupAction = null;
    ProxyLookup proxyLookup;

    public LayerListTopComponent() {
        initComponents();
   

        Collection<? extends MapExplorerManagerMgr> srvMapExp = Lookups.forPath("Core").lookupAll(MapExplorerManagerMgr.class);
        if (srvMapExp.iterator().hasNext()) {
            this.explorerManagerMgr = srvMapExp.iterator().next();
        } else {
            this.explorerManagerMgr = MapExplorerManagerMgr.getDefault();
        }

        setName(Bundle.CTL_LayerListTopComponent());
        setToolTipText(Bundle.HINT_LayerListTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);

        mapBean = mapBeanX;

        explorerManagerMgr.addMapExplorerManager(mapBean);
        explorerManagerMgr.addChangeListener(this);
        initActions();

        associateLookup(this.lookupAction);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnl = new javax.swing.JPanel();
        btnAddLayer = new javax.swing.JButton();
        lblTest = new javax.swing.JLabel();
        view = new org.openide.explorer.view.BeanTreeView();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(LayerListTopComponent.class, "LayerListTopComponent.jLabel1.text")); // NOI18N

        setLayout(new java.awt.BorderLayout());

        pnl.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnl.setPreferredSize(new java.awt.Dimension(10, 26));
        pnl.setLayout(new javax.swing.BoxLayout(pnl, javax.swing.BoxLayout.LINE_AXIS));

        btnAddLayer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnAddLayer, org.openide.util.NbBundle.getMessage(LayerListTopComponent.class, "LayerListTopComponent.btnAddLayer.text")); // NOI18N
        btnAddLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLayerActionPerformed(evt);
            }
        });
        pnl.add(btnAddLayer);

        org.openide.awt.Mnemonics.setLocalizedText(lblTest, org.openide.util.NbBundle.getMessage(LayerListTopComponent.class, "LayerListTopComponent.lblTest.text")); // NOI18N
        pnl.add(lblTest);

        add(pnl, java.awt.BorderLayout.NORTH);

        view.setRootVisible(false);
        add(view, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLayerActionPerformed

//        if (this.getExplorerManager().getRootContext().getClass().isInstance(InternalMapNode.class)) {
            InternalMapNode mn = (InternalMapNode) this.getExplorerManager().getRootContext();
            if (mn.getBean().isActive()) {
                MapItemsFactory factory = mn.getFactory();
                var chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setFileFilter(new FileNameExtensionFilter("SHP files", "shp", "shp"));
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setMultiSelectionEnabled(true);
                int result = chooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    var files = chooser.getSelectedFiles();
                    for (int i = 0; i < files.length; i++) {
                        var f = files[i];
                        if (LayerFileFilter.LAYER_FILEFILTER.accept(f)) {
                            LayerBean lb = new LayerBean(f.getName());
                            lb.setConnectionStr(f.getAbsolutePath());
                            factory.add(lb);
                        }
                    }
                }
//            }
        }

    }//GEN-LAST:event_btnAddLayerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLayer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTest;
    private javax.swing.JPanel pnl;
    private org.openide.explorer.view.BeanTreeView view;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {

    }

    @Override
    public void componentClosed() {
//        result.removeLookupListener(this);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return this.explorerManagerMgr.getMapExplorerManager(mapBean);
    }

    private void initActions() {

        ActionMap map = this.getActionMap();
        ExplorerManager mgr = this.getExplorerManager();

        CutAction cut = SystemAction.get(CutAction.class);

        getActionMap().put(cut.getActionMapKey(), ExplorerUtils.actionCut(mgr));

        CopyAction copy = SystemAction.get(CopyAction.class);
        getActionMap().put(copy.getActionMapKey(), ExplorerUtils.actionCopy(mgr));

        PasteAction paste = SystemAction.get(PasteAction.class);
        getActionMap().put(paste.getActionMapKey(), ExplorerUtils.actionPaste(mgr));

        DeleteAction delete = SystemAction.get(DeleteAction.class);
        getActionMap().put(delete.getActionMapKey(), ExplorerUtils.actionDelete(mgr, true));

        this.lookupAction = ExplorerUtils.createLookup(mgr, map);
    }

    @Override
    public void stateChanged(ChangeEvent evt) {

        setActiveMapBean();
    }

    private void setActiveMapBean() {

        if (this.explorerManagerMgr.getActiveMapBean() != null) {
            this.mapBean = this.explorerManagerMgr.getActiveMapBean();
            initActions();
        } else {
            this.mapBean = this.mapBeanX;
        }
        this.lblTest.setText("ExpMgr. - " + mapBean.getName());
        view.addNotify();
    }

}
