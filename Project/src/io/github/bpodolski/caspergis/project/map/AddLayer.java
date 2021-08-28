/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.map;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.services.MapExplorerManagerMgr;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
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
        category = "Map",
        id = "io.github.bpodolski.caspergis.project.map.AddLayer"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/map/map_activate.png",
        displayName = "#CTL_AddLayer"
)
@ActionReferences({
    @ActionReference(path = "Menu/Map", position = 100),
    @ActionReference(path = "Toolbars/Map", position = 100)
})
@NbBundle.Messages("CTL_AddLayer=Add Layer")
public class AddLayer implements ActionListener {

    private MapBean context;
    //Serwis 
    MapExplorerManagerMgr explorerManagerMgr = null;

    public AddLayer(MapBean context) {
        this.context = context;
        Collection<? extends MapExplorerManagerMgr> srvMapExp = Lookups.forPath("Core").lookupAll(MapExplorerManagerMgr.class);
        if (srvMapExp.iterator().hasNext()) {
            this.explorerManagerMgr = srvMapExp.iterator().next();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (explorerManagerMgr != null) {
            MapBean mb = explorerManagerMgr.getActiveMapBean();
            if (mb.equals(context)){

            NotifyDescriptor d = new NotifyDescriptor.Confirmation(
                    "" + context.getName(),
                    "");
            DialogDisplayer.getDefault().notify(d);
            }else{
            NotifyDescriptor d = new NotifyDescriptor.Confirmation(
                    "ta mapa nie jest aktywna",
                    "");
            DialogDisplayer.getDefault().notify(d);
            }

        } else {
            NotifyDescriptor d = new NotifyDescriptor.Confirmation(
                    "Message",
                    "Title");
            DialogDisplayer.getDefault().notify(d);
        }

    }
}
