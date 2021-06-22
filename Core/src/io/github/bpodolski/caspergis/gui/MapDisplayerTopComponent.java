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
import javax.swing.ActionMap;
import org.openide.actions.CopyAction;
import org.openide.actions.PasteAction;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapDisplayerTopComponent extends TopComponent implements ExplorerManager.Provider {

    private final MapBean mapBean;
    MapBeanEnv regMapBean = null;
    private final ExplorerManager mgr = new ExplorerManager();

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
        setName(mapBean.getName());
        setToolTipText(mapBean.getName());

        this.mapBean = mapBean;
        
        initView();
        initActions();
//        
        regMapBean = new MapBeanEnv(this.mapBean);
        
        lookupMapBean= new AbstractLookup (this.instanceContent);        
        instanceContent.add(regMapBean);
        proxyLookup = new ProxyLookup(lookupAction, lookupMapBean);
        
        associateLookup(this.proxyLookup);
        
        this.pnlMap.add(this.mapPanel, java.awt.BorderLayout.CENTER);
        
        CgRegistry.topComponentMap.remove(mapBean);
        CgRegistry.topComponentMap.put(mapBean, this);
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
        testBtn = new javax.swing.JButton();

        view.setRootVisible(false);

        pnlMap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMap.setLayout(new java.awt.BorderLayout());

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        pnlMap.add(jScrollPane1, java.awt.BorderLayout.PAGE_END);

        org.openide.awt.Mnemonics.setLocalizedText(testBtn, org.openide.util.NbBundle.getMessage(MapDisplayerTopComponent.class, "MapDisplayerTopComponent.testBtn.text")); // NOI18N
        testBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(testBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(view, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
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
        return mgr;
    }

    private void initActions() {
        ActionMap map = this.getActionMap();

//        CutAction cut = SystemAction.get(CutAction.class);
//        getActionMap().put(cut.getActionMapKey(), ExplorerUtils.actionCut(mgr));

        CopyAction copy = SystemAction.get(CopyAction.class);
        getActionMap().put(copy.getActionMapKey(), ExplorerUtils.actionCopy(mgr));
//
        PasteAction paste = SystemAction.get(PasteAction.class);
        getActionMap().put(paste.getActionMapKey(), ExplorerUtils.actionPaste(mgr));
//
//        DeleteAction delete = SystemAction.get(DeleteAction.class);
//        getActionMap().put(delete.getActionMapKey(), ExplorerUtils.actionDelete(mgr, true));

        this.lookupAction = ExplorerUtils.createLookup(mgr, map);
    }
}
