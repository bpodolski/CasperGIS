/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.utils;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class LayerFileFilter implements FileFilter {

        private String sFileExt = "shp";
        private boolean isDir = false;
        private boolean isDirOnly = false;

        public void setIsDir(boolean isDir) {
            this.isDir = isDir;
        }

        public void setIsDirOnly(boolean isDirOnly) {
            this.isDirOnly = isDirOnly;
        }

        public LayerFileFilter() {
            this.sFileExt = "shp";
            this.isDir = false;
        }

        public LayerFileFilter(String sFileExt) {
            this.sFileExt = sFileExt;
            this.isDir = false;
        }

        public String getsFileExt() {
            return sFileExt;
        }

        public void setsFileExt(String sFileExt) {
            this.sFileExt = sFileExt;
        }

        @Override
        public boolean accept(File file) {
            boolean isOk = false;

            if (file.exists()) {
                if (this.isDir) {
                    if (this.isDirOnly) {
                        isOk = file.isDirectory();
                    } else {
                        isOk = true;
                    }

                } else if (file.isFile()) {
                    if (file.getAbsolutePath().toUpperCase().endsWith(sFileExt.toUpperCase())) {
                        isOk = true;
                    }
                }
            }

            return isOk;
        }
    }