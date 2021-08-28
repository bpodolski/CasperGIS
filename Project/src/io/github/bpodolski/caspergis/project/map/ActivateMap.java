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
        id = "io.github.bpodolski.caspergis.project.map.ActivateMap"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/map/map_activate.png",
        displayName = "#CTL_ActivateMap"
)
@ActionReferences({
    @ActionReference(path = "Menu/Map", position = 30, separatorBefore = -50),
    @ActionReference(path = "Toolbars/Map", position = 30, separatorBefore = -50)
})
@NbBundle.Messages("CTL_ActivateMap=Activate Map")
public class ActivateMap implements ActionListener {
    
    private final MapBean context;

    //Serwis 
    MapExplorerManagerMgr explorerManagerMgr;
    
    public ActivateMap(MapBean context) {
        this.context = context;
        Collection<? extends MapExplorerManagerMgr> srvMapExp = Lookups.forPath("Core").lookupAll(MapExplorerManagerMgr.class);
        if (srvMapExp.iterator().hasNext()) {
            this.explorerManagerMgr = srvMapExp.iterator().next();
        } else {
            this.explorerManagerMgr = MapExplorerManagerMgr.getDefault();
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        explorerManagerMgr.getActiveMapBean().setActive(false);
        context.setActive(true);
    }
}
