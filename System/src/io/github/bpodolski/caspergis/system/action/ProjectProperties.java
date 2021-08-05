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
import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Project",
        id = "io.github.bpodolski.caspergis.system.action.CTL_ProjectProperties"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/system/action/project_new.png",
        displayName = "#CTL_ProjectProperties"
)
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 50),
    @ActionReference(path = "Toolbars/Project", position = 50)
})
@Messages("CTL_ProjectProperties=Project Properties")
public class ProjectProperties implements ActionListener {

    private final ProjectBean context;

    public ProjectProperties(ProjectBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogDescriptor d = new DialogDescriptor(new JPanel(), context.getName()+" - project properties", true, null);
        DialogDisplayer.getDefault().createDialog(d).setVisible(true);
    }
}
