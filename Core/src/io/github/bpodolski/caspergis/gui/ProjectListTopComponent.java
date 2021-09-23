/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui;

import io.github.bpodolski.caspergis.CgRegistryCore;
import io.github.bpodolski.caspergis.Installer;
import io.github.bpodolski.caspergis.gui.nodes.ProjectsRootNode;
import io.github.bpodolski.caspergis.services.SystemMgr;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import org.netbeans.api.io.IOProvider;
import org.netbeans.api.io.InputOutput;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.PasteAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//io.github.bpodolski.caspergis.gui//ProjectList//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ProjectListTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "io.github.bpodolski.caspergis.gui.ProjectListTopComponent")
@ActionReference(path = "Menu/Window", position = 313)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ProjectListAction",
        preferredID = "ProjectListTopComponent"
)
@Messages({
    "CTL_ProjectListAction=ProjectList",
    "CTL_ProjectListTopComponent=ProjectList Window",
    "HINT_ProjectListTopComponent=This is a ProjectList window"
})
public final class ProjectListTopComponent extends TopComponent implements ExplorerManager.Provider, PropertyChangeListener {
    
    private final ExplorerManager mgr = new ExplorerManager();
    private SystemMgr systemMgr;
    CgRegistryCore cgr = Installer.cgRegistry;
    InputOutput io = IOProvider.getDefault().getIO("Output", false);
    
    public ProjectListTopComponent() throws IntrospectionException, PropertyVetoException {
        initComponents();
        
        systemMgr = Lookups.forPath("System").lookupAll(SystemMgr.class).iterator().next();
        systemMgr.addPropertyChangeListener(this);
        systemMgr.initSystemDAO();
        
        setName(Bundle.CTL_ProjectListTopComponent());
        setToolTipText(Bundle.HINT_ProjectListTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        
        initActions();
        associateLookup(ExplorerUtils.createLookup(mgr, getActionMap()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl = new javax.swing.JPanel();
        btnAddProject = new javax.swing.JButton();
        view = new org.openide.explorer.view.BeanTreeView();

        setLayout(new java.awt.BorderLayout());

        pnl.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnl.setMinimumSize(new java.awt.Dimension(49, 35));
        pnl.setPreferredSize(new java.awt.Dimension(10, 32));
        pnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 3));

        org.openide.awt.Mnemonics.setLocalizedText(btnAddProject, org.openide.util.NbBundle.getMessage(ProjectListTopComponent.class, "ProjectListTopComponent.btnAddProject.text")); // NOI18N
        btnAddProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProjectActionPerformed(evt);
            }
        });
        pnl.add(btnAddProject);

        add(pnl, java.awt.BorderLayout.SOUTH);

        view.setRootVisible(false);
        view.setRowHeader(null);
        view.setRowHeaderView(null);
        add(view, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProjectActionPerformed
//        ProjectBean bean = new ProjectBean();
//        CgRegistry.systemFactory.add(bean);
    }//GEN-LAST:event_btnAddProjectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProject;
    private javax.swing.JPanel pnl;
    private org.openide.explorer.view.BeanTreeView view;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        try {
            initView();
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        } catch (PropertyVetoException ex) {
            Exceptions.printStackTrace(ex);
        }
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
    
    @Override
    public ExplorerManager getExplorerManager() {
        return mgr; //To change body of generated methods, choose Tools | Templates.
    }
    
    private void initActions() {
        CutAction cut = SystemAction.get(CutAction.class);
        getActionMap().put(cut.getActionMapKey(), ExplorerUtils.actionCut(mgr));
        
        CopyAction copy = SystemAction.get(CopyAction.class);
        getActionMap().put(copy.getActionMapKey(), ExplorerUtils.actionCopy(mgr));
        
        PasteAction paste = SystemAction.get(PasteAction.class);
        getActionMap().put(paste.getActionMapKey(), ExplorerUtils.actionPaste(mgr));
        
        DeleteAction delete = SystemAction.get(DeleteAction.class);
        getActionMap().put(delete.getActionMapKey(), ExplorerUtils.actionDelete(mgr, true));
    }
    
    public void initView() throws IntrospectionException, PropertyVetoException {
        Node rootNode;
        if (systemMgr.isSystemDAOready()) {
            
            rootNode = new ProjectsRootNode(systemMgr.getModelProjectList());
            rootNode.setDisplayName("System");
            this.view.setRootVisible(false);
        } else {
            rootNode = new AbstractNode(Children.LEAF);
            rootNode.setDisplayName("SYSTEM ERROR");
            this.view.setRootVisible(true);
        }
        mgr.setRootContext(rootNode);
        rootNode.setPreferred(false);
        
        expandTreeNode(rootNode);
        
    }
    
    private void expandTreeNode(final Node node) {
        view.expandNode(node);
        
        Children children = node.getChildren();
        if (null != children) {
            int nodeCount = children.getNodesCount();
            for (int nI = 0; nI < nodeCount; nI++) {
                expandTreeNode(children.getNodeAt(nI));
            }
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals("SYSTEM_DAO_READY")) {
            if((boolean) evt.getNewValue()) try {
                this.initView();
            } catch (IntrospectionException | PropertyVetoException ex) {
                Exceptions.printStackTrace(ex);
            }

        }
    }
    
}
