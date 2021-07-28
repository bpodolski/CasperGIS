/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.action.wizard;

import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.filesystems.FileChooserBuilder;

public final class NewProjectVisualPanel1 extends JPanel {

    /**
     * Creates new form NewProjectVisualPanel1
     */
    public NewProjectVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return "Basic information";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtProjectFile = new javax.swing.JTextField();
        btnProjectFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edtProjectTitle = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtAuthor = new javax.swing.JTextField();

        edtProjectFile.setText(org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.edtProjectFile.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnProjectFile, org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.btnProjectFile.text")); // NOI18N
        btnProjectFile.setToolTipText(org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.btnProjectFile.toolTipText")); // NOI18N
        btnProjectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjectFileActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.jLabel2.text")); // NOI18N

        edtProjectTitle.setText(org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.edtProjectTitle.text")); // NOI18N

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.jLabel4.text")); // NOI18N

        edtAuthor.setText(org.openide.util.NbBundle.getMessage(NewProjectVisualPanel1.class, "NewProjectVisualPanel1.edtAuthor.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnProjectFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtProjectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(edtProjectTitle)
                    .addComponent(jScrollPane1)
                    .addComponent(edtAuthor)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtProjectFile)
                    .addComponent(btnProjectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtProjectTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnProjectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjectFileActionPerformed
        FileFilter cgFIleFilter = new FileNameExtensionFilter("Casper GIS project file(.cgpr)", "cgpr");

        File toAdd = new FileChooserBuilder("Open Project")
                .setTitle("Open Casper GIS Project")
                .setApproveText("Open")
                .setFileFilter(cgFIleFilter)
                .setAcceptAllFileFilterUsed(false)
                .showSaveDialog();
        if (toAdd != null) {
            String sFile = toAdd.getAbsolutePath();
            if (!sFile.endsWith(".cgpr")) {
                sFile += ".cgpr";
            }
            this.edtProjectFile.setText(sFile);
            if (this.edtProjectTitle.getText().equals("")) {
                this.edtProjectTitle.setText(toAdd.getName().replace(".cgpr", ""));
            }
        }

    }//GEN-LAST:event_btnProjectFileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProjectFile;
    private javax.swing.JTextField edtAuthor;
    private javax.swing.JTextField edtProjectFile;
    private javax.swing.JTextField edtProjectTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables
public JTextField getProjectFile() {
        return this.edtProjectFile;
    }

    public JTextField getProjectTitle() {
        return this.edtProjectTitle;
    }

    public JTextField getProjectAuthor() {
        return this.edtAuthor;
    }

    public JTextArea getProjectDescription() {
        return this.txtDescription;
    }

}
