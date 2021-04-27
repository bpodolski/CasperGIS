/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import java.io.File;
import org.openide.util.NbPreferences;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class CasperInfo {

    public static final String EXIF_TOOL_ZIP = "/pl/com/caspergis/externaltools/res/exiftool.zip";
    public static final String IMAGE_MAGICK_ZIP = "/pl/com/caspergis/externaltools/res/imagemagick.zip";
    public static final String GDAL_ZIP = "/pl/com/caspergis/externaltools/res/gdal.zip";

    public static String userWorkspace = System.getProperty("netbeans.user") + File.separatorChar + "UserWorkspace"; // katalog podręczny aplikacji dla użytkownika
    public static String userWorkspaceTmp = userWorkspace + File.separatorChar + "tmp"; // katalog podręczny aplikacji dla użytkownika

    public static String DB_SYSTEM_PATH = userWorkspace + File.separatorChar + "CasperSystem.cgpr"; // katalog podręczny aplikacji dla użytkownika
    public static String DB_DEFAULT_PROJECT_PATH = userWorkspace + File.separatorChar + "DefaultCasperProject.cgpr";

    public static String appExiftool = System.getProperty("CasperGIS.exiftool.exiftool");
    public static String appConvert = System.getProperty("CasperGIS.imagemagick.convert");
    public static String appGdalTranslate = System.getProperty("CasperGIS.gdal.gdal_translate");
    public static String appOgr2ogr = System.getProperty("CasperGIS.gdal.ogr2ogr");

    static void nbPreferences() {
// katalog instalacji aplikacji 
        NbPreferences.forModule(CasperInfo.class).put("CGAPP_HOME", System.getProperty("netbeans.home"));
// katalog podręczny aplikacji dla użytkownika 
        NbPreferences.forModule(CasperInfo.class).put("CG_USER", System.getProperty("netbeans.user"));
// systemowy katalog użytkownika
        NbPreferences.forModule(CasperInfo.class).put("CG_USER_HOME", System.getProperty("user.home"));
// katalog aplikacji dla użytkownika
        NbPreferences.forModule(CasperInfo.class).put("CG_USER_WORKSCAPE", System.getProperty("netbeans.user") + File.separatorChar + "UserWorkspace");
// katalog podręczny aplikacji dla użytkownika
        NbPreferences.forModule(CasperInfo.class).put("CG_USER_TMPWORKSCAPE", System.getProperty("netbeans.user") + File.separatorChar + "UserWorkspace" + File.separatorChar + "tmp");


// Path to system DB config
        NbPreferences.forModule(CasperInfo.class).put("DB_SYSTEM_PATH", NbPreferences.forModule(CasperInfo.class).get("CG_USER_WORKSCAPE","") + File.separatorChar + "CasperSystem.cgpr");
// Path to default project
        NbPreferences.forModule(CasperInfo.class).put("DB_DEFAULT_PROJECT_PATH", NbPreferences.forModule(CasperInfo.class).get("CG_USER_WORKSCAPE","") + File.separatorChar + "DefaultCasperProject.cgpr");
    }

}
