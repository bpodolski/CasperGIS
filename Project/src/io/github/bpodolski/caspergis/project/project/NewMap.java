/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.project;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.MapListMgr;
import io.github.bpodolski.caspergis.services.ProjectListMgr;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

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
public class NewMap implements ActionListener {

    private final ProjectBean context;

    public NewMap(ProjectBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogDescriptor.InputLine dd = new DialogDescriptor.InputLine("Map name:", "New map Dialog");
        dd.setInputText("Layers");
        if (DialogDisplayer.getDefault().notify(dd).equals(DialogDescriptor.OK_OPTION) && !dd.getInputText().isEmpty()) {
            String s = (String) dd.getInputText();
            
            MapListMgr mapListCoreService = Lookups.forPath("Core").lookupAll(MapListMgr.class).iterator().next();
            MapListMgr mapListProjectService = Lookups.forPath("Project").lookupAll(MapListMgr.class).iterator().next();
            MapBean mapBean = new MapBean(null,s);
            mapListProjectService.add(mapBean, context);
            mapListCoreService.add(mapBean, context);
            
            

        }
    }
}
