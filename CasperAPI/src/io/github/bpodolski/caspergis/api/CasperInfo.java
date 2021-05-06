/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.api;

import java.io.File;
import org.netbeans.api.io.IOProvider;
import org.netbeans.api.io.InputOutput;
import org.openide.util.NbPreferences;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class CasperInfo {

    /**
     * Helper to get Otput
     */
    public static final InputOutput io = IOProvider.getDefault().getIO("Output", false);
    
    /**
     *Name of NbProperties - Path to NB Platform Appp instal directory
     */
    public static final String CGAPP_HOME = "CGAPP_HOME";

    /**
     *Name of NbProperties - Path to user directory
     */
    public static final String CG_USER = "CG_USER";

    /**
     *Name of NbProperties - Path to user directory
     */
    public static final String CG_USER_HOME = "CG_USER_HOME";

    /**
     *Name of NbProperties - Path to user workspace directory
     */
    public static final String CG_USER_WORKSCAPE = "CG_USER_WORKSCAPE";

    /**
     *Name of NbProperties - Path to user temporary directory
     */
    public static final String CG_USER_TMPWORKSCAPE = "CG_USER_TMPWORKSCAPE";

    /**
     *Name of NbProperties - Path to system database
     */
    public static final String DB_SYSTEM_PATH = "DB_SYSTEM_PATH";

    /**
     *Name of NbProperties - Path to default project
     */
    public static final String DB_DEFAULT_PROJECT_PATH = "DB_DEFAULT_PROJECT_PATH";

    /**
     *Name of NbProperties - - Path for EXIFTOOL tool - exiftool
     * Tool need to manipulate EXIF tags of foto, usefull for geotaging
     */
    public static final String EXT_TOOL_EXIFTOOL = "EXT_TOOL_EXIFTOOL";

    /**
     *Name of NbProperties - - Path for IMAGEMAGICK tool - convert
     * Tool for images manipulation
     */
    public static final String EXT_TOOL_IMGCONVERT = "EXT_TOOL_IMGCONVERT";

    /**
     *Name of NbProperties - Path for GDAL tool - ogr2ogr
     *A command line tool that converts one data source to another Ogr data source.
     */
    public static final String EXT_TOOL_GDALOGR2OGR = "EXT_TOOL_GDALOGR2OGR";

    /**
     *Name of NbProperties - Path for GDAL tool - gdal_translate 
     * The gdal_translate utility can be used to convert raster data between different formats,
     * and make some others operations. 
     */
    public static final String EXT_TOOL_GDALTRANSLATE = "EXT_TOOL_GDALTRANSLATE";

    /**
     * Put NbPreferences
     */
    public static void nbPreferences() {
 
        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.CGAPP_HOME, System.getProperty("netbeans.home"));

        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.CG_USER, System.getProperty("netbeans.user"));

        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.CG_USER_HOME, System.getProperty("user.home"));

        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.CG_USER_WORKSCAPE, System.getProperty("netbeans.user") + File.separatorChar + "UserWorkspace");

        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.CG_USER_TMPWORKSCAPE, System.getProperty("netbeans.user") + File.separatorChar + "UserWorkspace" + File.separatorChar + "tmp");

        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.DB_SYSTEM_PATH, NbPreferences.forModule(CasperInfo.class).get("CG_USER_WORKSCAPE", "") + File.separatorChar + "CasperSystem.cgpr");

        NbPreferences.forModule(CasperInfo.class).put(CasperInfo.DB_DEFAULT_PROJECT_PATH, NbPreferences.forModule(CasperInfo.class).get("CG_USER_WORKSCAPE", "") + File.separatorChar + "DefaultCasperProject.cgpr");
    }

}
