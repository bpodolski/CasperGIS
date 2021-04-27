/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import io.github.bpodolski.caspergis.gui.StartPageTopComponent;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

public class Installer extends ModuleInstall {

    @Override
    public void validate() {
        /*variable show pokazująca czy platforma jest gotowa do pracy - wszystkie 
        pliki projektów, systemowe, mienne są ustawione i sprawdzone
        aby móc korzystać z różnych narzędzi*/
        NbPreferences.forModule(CasperInfo.class).put("CG_READY", "F");
        CasperInfo.nbPreferences();
    }
    
    @Override
    public void restored() {
        this.setLAF();
        this.invokeWhenUIReady();
    }

    private void setLAF() {
        NbPreferences.root().node("laf").put("laf", "com.formdev.flatlaf.FlatLightLaf");
    }

    private void invokeWhenUIReady() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                openStartPage();

            }

            private void openStartPage() {
                boolean noOpen = NbPreferences.forModule(StartPageTopComponent.class).get("isStartPage", "").toUpperCase().equals("N");
                StartPageTopComponent tc = (StartPageTopComponent) WindowManager.getDefault().findTopComponent("StartPageTopComponent");
                if (tc != null) {
                    if (!noOpen) {
                        tc.open();
                        tc.requestActive();
                    } else {
                        tc.close();
                    }
                }

            }
        });
    }
}
