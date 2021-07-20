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
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Project",
        id = "io.github.bpodolski.caspergis.system.action.DeleteProject"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/system/action/project_new.png",
        displayName = "#CTL_DeleteProject"
)
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 40),
    @ActionReference(path = "Toolbars/Project", position = 40)
})
@Messages("CTL_DeleteProject=Delete Project")
public class DeleteProject implements ActionListener {

    private final ProjectBean context;

    public DeleteProject(ProjectBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
