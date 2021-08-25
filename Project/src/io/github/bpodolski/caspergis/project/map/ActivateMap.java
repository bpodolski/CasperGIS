/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.map;

import io.github.bpodolski.caspergis.beans.MapBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.util.NbBundle;

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

    public ActivateMap(MapBean context) {
        this.context = context;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapBean mapBean = context;
        mapBean.setActive(!mapBean.isActive());
        Action ac = Actions.forID("Map", "io.github.bpodolski.caspergis.project.map.ActivateMap");
        if (context.isActive()) {
            ac.putValue(Action.NAME, "Deactivate Map");
        } else {
            ac.putValue(Action.NAME, "Activate Map");
        }
    }
}
