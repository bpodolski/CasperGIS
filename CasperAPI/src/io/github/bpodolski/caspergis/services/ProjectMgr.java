/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.util.List;
import org.openide.util.Lookup;

/**
 * Abstract class - The base for the service that provides: 
 * - managment of project properties
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class ProjectMgr {

    /**
     *
     * @param projectBean
     * @return
     */
    public abstract boolean setupProjectInfo(ProjectBean projectBean);

    public abstract void updateProjectInfo(ProjectBean projectBean);

    public static ProjectMgr getDefault() {
        ProjectMgr service = Lookup.getDefault().lookup(ProjectMgr.class);
        if (service == null) {
            service = new DefaultProjectMgr();
        }
        return service;
    }

    private static class DefaultProjectMgr extends ProjectMgr {

        @Override
        public boolean setupProjectInfo(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void updateProjectInfo(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
