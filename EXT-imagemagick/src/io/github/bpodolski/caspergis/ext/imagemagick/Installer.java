/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.ext.imagemagick;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.utils.CgUtils;
import java.io.File;
import java.io.IOException;
import org.openide.modules.ModuleInstall;
import org.openide.util.*;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        installExtTool();
    }

     private void installExtTool() {
        if (NbPreferences.forModule(CasperInfo.class).get(CasperInfo.EXT_TOOL_IMGCONVERT, "").equals("")) {
            if (CgUtils.isWindows()) {
                NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_IMGCONVERT, "[to be set by the user]");
                String sRes = "/io/github/bpodolski/caspergis/ext/imagemagick/res/imagemagick.zip";
                String sHome = NbPreferences.forModule(CasperInfo.class).get(CasperInfo.CG_USER, "");
                if (!sHome.equals("")) {
                    try {
                        CgUtils.unZipFile(this.getClass().getResourceAsStream(sRes), new File(sHome));
                        String sToolPath = sHome + "\\imagemagick\\convert.exe";
                        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_IMGCONVERT, sToolPath);
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);

                    }
                }
            } else {
                NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_IMGCONVERT, "[to be set by the user]");
            }

        }
    }
}
