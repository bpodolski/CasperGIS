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
import io.github.bpodolski.caspergis.services.ServiceProjectManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.Lookups;

@ActionID(
        category = "Project",
        id = "io.github.bpodolski.caspergis.system.action.CloseProject"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/system/action/project_new.png",
        displayName = "#CTL_CloseProject"
)
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 30),
    @ActionReference(path = "Toolbars/Project", position = 30)
})
@Messages("CTL_CloseProject=New Project")
public class CloseProject implements ActionListener {

    private final ProjectBean context;

    public CloseProject(ProjectBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceProjectManager projectService = Lookups.forPath("core").lookupAll(ServiceProjectManager.class).iterator().next();
        projectService.close(context);
    }
}
