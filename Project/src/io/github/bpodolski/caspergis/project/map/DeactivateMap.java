/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.map;

import io.github.bpodolski.caspergis.beans.MapBean;
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
        category = "Map",
        id = "io.github.bpodolski.caspergis.project.map.DeactivateMap"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/map/map_deactivate.png",
        displayName = "#CTL_DeactivateMap"
)
@ActionReferences({
    @ActionReference(path = "Menu/Map", position = 30, separatorBefore = -50),
    @ActionReference(path = "Toolbars/Map", position = 30, separatorBefore = -50)
})
@NbBundle.Messages("CTL_DeactivateMap=Dectivate Map")
public class DeactivateMap implements ActionListener {

    private final MapBean context;
    public DeactivateMap(MapBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.setActive(false);
    }

}
