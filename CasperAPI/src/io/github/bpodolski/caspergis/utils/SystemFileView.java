/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.utils;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class SystemFileView  extends FileView {

        @Override
        public Icon getIcon(File f) {
            FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            return fileSystemView.getSystemIcon(f);

        }
    }