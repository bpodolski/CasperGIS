/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

/**
 *
 * @author bpodolski
 */
public abstract class SystemMgr {
    
    public abstract void initSystemDAO();
    public abstract ModelProjectList getModelProjectList();
        /**
     * Get default temporary project path based on NbPreferences
     *
     * @return ProjectBean with path
     */
    public abstract ProjectBean getSystemProject();

    /**
     * Get registered in system data base project paths in order based on field
     * POSITION
     *
     * @return
     */
    public abstract List<ProjectBean> getProjectList();


    public abstract boolean isSystemDAOready();

    public abstract void setSystemDAOready(boolean systemDAOready);

    public abstract boolean isModelProjectListReady();

    public abstract void setModelProjectListReady(boolean modelProjectListReady);
    
     public abstract void addPropertyChangeListener(PropertyChangeListener listener);

    public abstract void removePropertyChangeListener(PropertyChangeListener listener);

    public abstract void addProject(File toAdd);
    public abstract void newProject(File toAdd);
    public abstract void updateProject(ProjectBean projectBean);
    public abstract void closeProject(ProjectBean projectBean);
    public abstract void deleteProject(ProjectBean projectBean);
    
    
}
