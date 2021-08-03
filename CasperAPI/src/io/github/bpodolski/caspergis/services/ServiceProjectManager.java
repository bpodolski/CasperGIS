/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 * Abstract class - The base for the service that provides the list of projects
 * from system database
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class ServiceProjectManager {

    /**
     * Get default temporary project path based on NbPreferences
     *
     * @return ProjectBean with path
     */
    public abstract ProjectBean getSystemProject();

    public abstract String getServiceName();
    /**
     * Get registered in system data base project paths in order based on field
     * POSITION
     *
     * @return
     */
    public abstract List<ProjectBean> getProjectList();

    /**
     * Delete project - delete from system projects list and delete project file
     *
     * @param projectBean
     */
    public abstract void delete(ProjectBean projectBean);

    /**
     * Delete project - delete from system projects list
     *
     * @param projectBean
     */
    public abstract void close(ProjectBean projectBean);

    /**
     * Update project - only position in system projects list and hidden value
     *
     * @param projectBean
     */
    public abstract void update(ProjectBean projectBean);

    /**
     * Add Project - only path value need
     *
     * @param projectBean
     */
    public abstract void add(ProjectBean projectBean);

    /**
     *
     * @return
     */
    public static ServiceProjectManager getDefault() {
        ServiceProjectManager projectGetterService = Lookup.getDefault().lookup(ServiceProjectManager.class);
        if (projectGetterService == null) {
            projectGetterService = new DefaultProjectGetter();
        }
        return projectGetterService;
    }

    private static class DefaultProjectGetter extends ServiceProjectManager {

        @Override
        public ProjectBean getSystemProject() {
            ProjectBean systemProject = new ProjectBean();
            return systemProject;
        }

        @Override
        public List<ProjectBean> getProjectList() {
            ArrayList<ProjectBean> projectList = new <ProjectBean>ArrayList();
            for (int i = 1; i < 4; i++) {
                ProjectBean pb = new ProjectBean();
                pb.setName(i + ".TestProject - DefImpl");
                projectList.add(pb);
            }
            return projectList;
        }

        @Override
        public void delete(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void update(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void close(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getServiceName() {
            return "DefaultProjectGetter";
        }

    }

}
