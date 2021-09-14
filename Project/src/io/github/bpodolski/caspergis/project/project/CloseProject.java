/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.project;

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
import org.openide.util.lookup.Lookups;

@ActionID(
        category = "Project",
        id = "io.github.bpodolski.caspergis.system.action.CloseProject"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/project/project_close.png",
        displayName = "#CTL_CloseProject"
)
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 30),
    @ActionReference(path = "Toolbars/Project", position = 30)
})
@Messages("CTL_CloseProject=Close Project")
public class CloseProject implements ActionListener {

    private final ProjectBean context;

    public CloseProject(ProjectBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectBean projectBean = context;
//        ProjectListMgr projectSystemService = Lookups.forPath("System").lookupAll(ProjectListMgr.class).iterator().next();
//        ProjectListMgr projectCoreService = Lookups.forPath("Core").lookupAll(ProjectListMgr.class).iterator().next();
//        ProjectListMgr projectProjectService = Lookups.forPath("Project").lookupAll(ProjectListMgr.class).iterator().next();

        
//        projectSystemService.close(projectBean);//delete from system DB 
//        projectCoreService.close(projectBean); //delete from view model
//        projectProjectService.close(projectBean);//close project DAO and delete project file
        
        
        
    }
}
