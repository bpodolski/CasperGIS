/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.project;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ActionID(
        category = "Project",
        id = "io.github.bpodolski.caspergis.system.action.NewMap"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/project/project_close.png",
        displayName = "#CTL_NewMap"
)
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 30),
    @ActionReference(path = "Toolbars/Project", position = 30)
})
@NbBundle.Messages("CTL_NewMap=New Map")
public class NewMap  implements ActionListener {
    private final ProjectBean context;

    public NewMap(ProjectBean context) {
        this.context = context;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
