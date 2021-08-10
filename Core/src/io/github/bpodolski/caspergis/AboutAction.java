/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import io.github.bpodolski.caspergis.gui.AboutBoxPnl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Help",
        id = "io.github.bpodolski.caspergis.AboutAction"
)
@ActionRegistration(
        displayName = "#CTL_AboutAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Help", position = 10)
})
@Messages("CTL_AboutAction=About")
public final class AboutAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        NotifyDescriptor d = new NotifyDescriptor.Message("Information");
        d.setTitle("About Program");
        d.setMessage(new AboutBoxPnl());
        d.setMessageType(NotifyDescriptor.PLAIN_MESSAGE);
        DialogDisplayer.getDefault().notify(d);
    }
}
