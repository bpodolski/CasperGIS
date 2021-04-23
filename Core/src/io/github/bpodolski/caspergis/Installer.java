/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import com.formdev.flatlaf.FlatLightLaf;
import io.github.bpodolski.caspergis.gui.StartPageTopComponent;
import javax.swing.Action;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.openide.filesystems.FileUtil;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        this.setLAF();
        this.invokeWhenUIReady();
    }

    private void setLAF() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void invokeWhenUIReady() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                boolean isStartPage = NbPreferences.forModule(StartPageTopComponent.class).get("isStartPage", "").toUpperCase().equals("N");

                if (!isStartPage) {
                    StartPageTopComponent tc = (StartPageTopComponent) WindowManager.getDefault().findTopComponent("StartPageTopComponent");
                    if (tc != null) {
                        tc.open();
                        tc.requestActive();
                        // TODO add action: "Actions/Window/org-netbeans-core-windows-actions-MaximizeWindowAction.instance"
                    }
                }

            }
        });
    }
}
