/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.ext.gdal;

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
        if (NbPreferences.forModule(CasperInfo.class).get(CasperInfo.EXT_TOOL_GDALOGR2OGR, "").equals("")) {
            if (CgUtils.isWindows()) {
                NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_GDALOGR2OGR, "[to be set by the user]");
                NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_GDALTRANSLATE, "[to be set by the user]");
                String sRes = "/io/github/bpodolski/caspergis/ext/gdal/res/gdal.zip";
                String sHome = NbPreferences.forModule(CasperInfo.class).get(CasperInfo.CG_USER, "");
                if (!sHome.equals("")) {
                    try {
                        CgUtils.unZipFile(this.getClass().getResourceAsStream(sRes), new File(sHome));
                        String sToolPath1 = sHome + "\\gdal\\ogr2ogr.exe";
                        String sToolPath2 = sHome + "\\gdal\\gdal_translate.exe";
                        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_GDALOGR2OGR, sToolPath1);
                        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_GDALTRANSLATE, sToolPath2);
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);

                    }
                }
            } else {
                NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_GDALOGR2OGR, "[to be set by the user]");
                NbPreferences.forModule(CasperInfo.class).put(CasperInfo.EXT_TOOL_GDALTRANSLATE, "[to be set by the user]");
            }

        }
    }
    
   
}
