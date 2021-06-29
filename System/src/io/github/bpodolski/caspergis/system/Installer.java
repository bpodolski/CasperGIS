/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system;

import io.github.bpodolski.caspergis.api.CasperInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        testSystemDB();
        
    }

    private void testSystemDB() {
               
        URL inputUrl = getClass().getResource("/io/github/bpodolski/caspergis/system/res/CasperSystem.cgpr");
        File dest = new File(NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_SYSTEM_PATH, ""));
        if (!dest.exists())
        try {
            FileUtils.copyURLToFile(inputUrl, dest);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
