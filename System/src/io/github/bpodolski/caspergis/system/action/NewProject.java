/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Project",
        id = "io.github.bpodolski.caspergis.system.action.NewProject"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/system/action/project_new.png",
        displayName = "#CTL_AddProject"
)
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 20),
    @ActionReference(path = "Toolbars/Project", position = 20)
})
@Messages("CTL_AddProject=New Project")
public final class NewProject implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
