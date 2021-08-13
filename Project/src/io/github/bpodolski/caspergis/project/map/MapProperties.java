/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.map;

import io.github.bpodolski.caspergis.beans.MapBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
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
        id = "io.github.bpodolski.caspergis.project.map.MapProperties"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/map/map_activate.png",
        displayName = "#CTL_MapProperties"
)
@ActionReferences({
    @ActionReference(path = "Menu/Map", position = 1000, separatorBefore = -50),
    @ActionReference(path = "Toolbars/Map", position = 1000)
})
@NbBundle.Messages("CTL_MapProperties=Map Properties")
public class MapProperties implements ActionListener {

    private final MapBean context;

    public MapProperties(MapBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String initialValue = "Purchase";
        String cashOut = "Cashout";
        String cashOut2 = "Cashout2";
        String[] options = new String[]{initialValue, cashOut, cashOut2};

        NotifyDescriptor d = new NotifyDescriptor(
                new PnlMapProperties1(), // message
                "Map properties", // title
                NotifyDescriptor.OK_CANCEL_OPTION, // option type
                NotifyDescriptor.PLAIN_MESSAGE, // message type
                options, // own buttons as Object[]
                initialValue); // initial value
        DialogDisplayer.getDefault().notify(d);

        String selectedValue = (String) DialogDisplayer.getDefault().notify(d);
        if (selectedValue.equals(initialValue)) {
            // handle Purchase
        } else if (selectedValue.equals(cashOut)) {
            // handle Cashout   
        } else {
            // dialog closed with top close button
        }

    }
}
