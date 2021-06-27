/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.gui.StartPageTopComponent;
import java.io.File;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

public class Installer extends ModuleInstall {

    public static CgRegistry cgRegistry = new CgRegistry();
    
    @Override
    public void validate() {
       
        //test - first run of CasperGIS app
        if (NbPreferences.forModule(CasperInfo.class).get("FIRST_RUN", "").equals("")) {
            CasperInfo.nbPreferences();
            createWorkscape();
            NbPreferences.forModule(CasperInfo.class).put("FIRST_RUN", "F");
        }

        /*variable show pokazująca czy platforma jest gotowa do pracy - wszystkie 
        pliki projektów, systemowe, mienne są ustawione i sprawdzone
        aby móc korzystać z różnych narzędzi*/
        NbPreferences.forModule(CasperInfo.class).put("CG_READY", "F");

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

    private void createWorkscape() {
        File fUserWorkspace = new File(NbPreferences.forModule(CasperInfo.class).get("CG_USER_WORKSCAPE", ""));
        File fUserWorkspaceTmp = new File(NbPreferences.forModule(CasperInfo.class).get("CG_USER_TMPWORKSCAPE", ""));
        fUserWorkspace.mkdir();
        fUserWorkspaceTmp.mkdir();
    }

}
