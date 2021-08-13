/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.layer;

import io.github.bpodolski.caspergis.beans.LayerBean;
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
        category = "Layer",
        id = "io.github.bpodolski.caspergis.project.layer.AddLayer"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/map/map_activate.png",
        displayName = "#CTL_DeleteLayer"
)
@ActionReferences({
    @ActionReference(path = "Menu/Layer", position = 100),
    @ActionReference(path = "Toolbars/Layer", position = 100)
})
@NbBundle.Messages("CTL_DeleteLayer=Delete Layer")
public class DeleteLayer  implements ActionListener {

    private final LayerBean context;

    public DeleteLayer(LayerBean context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LayerBean layerBean = context;

    }
}
