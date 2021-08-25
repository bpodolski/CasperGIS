/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.gui.geotools.JMapPanelCG;
import io.github.bpodolski.caspergis.gui.nodes.InternalMapNode;
import io.github.bpodolski.caspergis.gui.nodes.services.CoreMapExplorerManagerMgr;
import io.github.bpodolski.caspergis.services.MapExplorerManagerMgr;
import java.beans.IntrospectionException;
import java.util.Collection;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.PasteAction;
import org.openide.awt.Actions;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapDisplayerTopComponent extends TopComponent implements ExplorerManager.Provider, ChangeListener {

    private final MapBean mapBean;
    private ExplorerManager mgr = null;

    MapExplorerManagerMgr explorerManagerMgr;// = CoreMapExplorerManagerMgr.getDefault();

    InstanceContent instanceContent = new InstanceContent();

    Lookup lookupMapBean = null;
    Lookup lookupAction = null;
    ProxyLookup proxyLookup;

    JMapPanelCG mapPanel = new JMapPanelCG();

    /**
     * Creates new form MapDisplayerTopComponent
     */
    public MapDisplayerTopComponent(MapBean mapBean) {
        initComponents();

        Collection<? extends MapExplorerManagerMgr> srvMapExp = Lookups.forPath("Core").lookupAll(MapExplorerManagerMgr.class);
        if (srvMapExp.iterator().hasNext()) {
            this.explorerManagerMgr = srvMapExp.iterator().next();
        } else {
            this.explorerManagerMgr = MapExplorerManagerMgr.getDefault();
        }

        this.mapBean = mapBean;
        this.setName(mapBean.getName() + " - editor");

        lookupMapBean = new AbstractLookup(this.instanceContent);
        instanceContent.add(mapBean);

        this.pnlMap.add(this.mapPanel, java.awt.BorderLayout.CENTER);
        this.mgr = this.explorerManagerMgr.getMapExplorerManager(mapBean);

        setTopComponentProps();
        initView();
        initActions();
        proxyLookup = new ProxyLookup(lookupAction, lookupMapBean);
        associateLookup(this.proxyLookup);

        Action ac = Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.ActivateMap");
        this.btnTest.setAction(ac);

        this.mapBean.addChangeListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        view = new org.openide.explorer.view.BeanTreeView();
        pnlMap = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        btnTest = new javax.swing.JButton();

        pnlMap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMap.setLayout(new java.awt.BorderLayout());

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        pnlMap.add(jScrollPane1, java.awt.BorderLayout.PAGE_END);

        org.openide.awt.Mnemonics.setLocalizedText(btnTest, org.openide.util.NbBundle.getMessage(MapDisplayerTopComponent.class, "MapDisplayerTopComponent.btnTest.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(view, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTest, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTest;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlMap;
    private javax.swing.JTextArea txt;
    private org.openide.explorer.view.BeanTreeView view;
    // End of variables declaration//GEN-END:variables
@Override
    public void componentOpened() {

    }

    @Override
    public void componentClosed() {

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
        return explorerManagerMgr.getMapExplorerManager(this.mapBean);
        //mgr;
    }

    private void initActions() {
        ActionMap map = this.getActionMap();

        CopyAction copy = SystemAction.get(CopyAction.class);
        getActionMap().put(copy.getActionMapKey(), ExplorerUtils.actionCopy(mgr));

        if (this.mapBean.isActive()) {
            CutAction cut = SystemAction.get(CutAction.class);
            getActionMap().put(cut.getActionMapKey(), ExplorerUtils.actionCut(mgr));

            PasteAction paste = SystemAction.get(PasteAction.class);
            getActionMap().put(paste.getActionMapKey(), ExplorerUtils.actionPaste(mgr));

            DeleteAction delete = SystemAction.get(DeleteAction.class);
            getActionMap().put(delete.getActionMapKey(), ExplorerUtils.actionDelete(mgr, true));
        }
        this.lookupAction = ExplorerUtils.createLookup(mgr, map);
    }

    @Override
    public String getName() {
        return this.mapBean.getName();
    }

    private void setTopComponentProps() {
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.FALSE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.FALSE);
        putClientProperty(TopComponent.PROP_SLIDING_DISABLED, Boolean.FALSE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.FALSE);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (this.mapBean.isActive()) {
            this.txt.setText(this.mapBean.getName());
        } else {
            this.txt.setText(">>>");
        }
    }
}
