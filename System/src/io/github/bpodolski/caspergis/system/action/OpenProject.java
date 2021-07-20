/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.action;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Project",
        id = "io.github.bpodolski.caspergis.system.action.OpenProject"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/system/action/project_new.png",
        displayName = "#CTL_OpenProject"
)
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 10),
    @ActionReference(path = "Toolbars/Project", position = 10)
})
@Messages("CTL_OpenProject=Open Project")
public class OpenProject implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
       FileFilter cgFIleFilter = new FileNameExtensionFilter("Casper GIS project file(.cgpr)","cgpr");
        
        File toAdd = new FileChooserBuilder("Open Project")
                .setTitle("Open Casper GIS Project")
                .setApproveText("Open")
                .setFileFilter(cgFIleFilter)
                .setAcceptAllFileFilterUsed(false)
                .showOpenDialog();

    }
}
