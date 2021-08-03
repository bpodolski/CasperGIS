/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.action.wizard;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.ProjectInfoService;
import io.github.bpodolski.caspergis.services.ServiceProjectManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
@ActionID(category = "Project", id = "io.github.bpodolski.caspergis.system.action.wizard.NewProjectWizardAction")
@ActionRegistration(iconBase = "io/github/bpodolski/caspergis/system/action/project_new.png", displayName = "#CTL_AddProject")
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 20),
    @ActionReference(path = "Toolbars/Project", position = 20)
})
@NbBundle.Messages("CTL_AddProject=New Project")
public final class NewProjectWizardAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new NewProjectWizardPanel1());
        panels.add(new NewProjectWizardPanel2());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(panels));
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle("Create new CasperGIS Project");

        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            String sProjectFile = (String) wiz.getProperty("ProjectFile");
            String sProjectTitle = (String) wiz.getProperty("ProjectTitle");
            String sProjectAuthor = (String) wiz.getProperty("ProjectAuthor");
            String sProjectDescription = (String) wiz.getProperty("ProjectDescription");

            ProjectBean projectBean = new ProjectBean();
//            
            projectBean.setName(sProjectTitle);
            projectBean.setPath(sProjectFile);

            ServiceProjectManager projectService = Lookups.forPath("System").lookupAll(ServiceProjectManager.class).iterator().next();
            projectService.add(projectBean);
            ServiceProjectManager projectService2 = Lookups.forPath("Core").lookupAll(ServiceProjectManager.class).iterator().next();
            projectService2.add(projectBean);


        }
    }

}
