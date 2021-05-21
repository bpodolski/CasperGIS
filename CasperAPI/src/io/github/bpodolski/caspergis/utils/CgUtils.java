/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.netbeans.api.io.IOProvider;
import org.netbeans.api.io.InputOutput;
import org.openide.util.Exceptions;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class CgUtils {

    /**
     * Helper to get Output
     */
    public static final InputOutput io = IOProvider.getDefault().getIO("Output", false);

    private static final String OS = System.getProperty("os.name").toLowerCase();

    /**
     * Unpacking the zip archive contained in the package into a directory
     *
     * @param source InputStream with archive from package
     * @param fDir destinaton directory
     * @throws IOException
     */
    public static void unZipFile(InputStream source, File fDir) throws IOException {

        try {
            try (ZipInputStream zipIn = new ZipInputStream(source)) {
                ZipEntry entry = zipIn.getNextEntry();
                // iterates over entries in the zip file
                while (entry != null) {
                    String filePath = fDir.getPath() + File.separator + entry.getName();
                    if (!entry.isDirectory()) {
                        // if the entry is a file, extracts it
                        extractFile(zipIn, filePath);
                    } else {
                        // if the entry is a directory, make the directory
                        File dir = new File(filePath);
                        dir.mkdirs();
                    }
                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }
            }
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }

    /**
     * Test system
     *
     * @return true if Windows
     */
    public static boolean isWindows() {

        return (OS.contains("win"));

    }

    /**
     * Test system
     *
     * @return true if Mac
     */
    public static boolean isMac() {

        return (OS.contains("mac"));

    }

    /**
     * Test system
     *
     * @return true if Linux/Unix
     */
    public static boolean isUnix() {

        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);

    }

    /**
     * Test system
     *
     * @return true if Solaris
     */
    public static boolean isSolaris() {

        return (OS.contains("sunos"));

    }

    /**
     * Copying files via stream
     *
     * @param source source file
     * @param dest destination file
     * @throws IOException
     */
    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    /**
     * Helper to get integer ID for object
     * @return integer ID for object
     */
    public static int createIntId() {
        LocalDateTime now = LocalDateTime.now();
        return (int) now.toEpochSecond(ZoneOffset.UTC);
    }
}
