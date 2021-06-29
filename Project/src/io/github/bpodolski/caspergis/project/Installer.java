/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project;

import io.github.bpodolski.caspergis.api.CasperInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openide.modules.ModuleInstall;
import org.openide.util.*;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        testSystemProjectDB();
    }

    private void testSystemProjectDB() {
        URL inputUrl = getClass().getResource("/io/github/bpodolski/caspergis/project/res/DefaultCasperProject.cgpr");
        File dest = new File(NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_DEFAULT_PROJECT_PATH, ""));
        if (!dest.exists())
        try {
            FileUtils.copyURLToFile(inputUrl, dest);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
