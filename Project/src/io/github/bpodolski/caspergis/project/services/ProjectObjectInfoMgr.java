/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.interfaces.DaoInterface;

import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgProjectInfo;
import io.github.bpodolski.caspergis.services.ProjectObjectMgr;
import java.io.File;
import java.util.HashMap;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = ProjectObjectMgr.class, path = "Project")
public class ProjectObjectInfoMgr extends ProjectObjectMgr {

    private HashMap cgProjectDaoMap = new HashMap<ProjectBean, ProjectDAO>();

    @Override
    public boolean setupProjectInfo(ProjectBean projectBean) {
        ProjectDAO dao;
        File fProject = new File(projectBean.getPath());        
        dao = new ProjectDAO(projectBean.getPath(), !fProject.exists());
        cgProjectDaoMap.put(projectBean, dao);
        
        CgProjectInfo pi = dao.getProjectInfo();
        pi.setName(projectBean.getName());
        pi.setPath(projectBean.getPath());
        dao.saveProjectInfo(pi);

        return true;
    }

    @Override
    public void updateProjectInfo(ProjectBean projectBean) {
        ProjectDAO dao = (ProjectDAO) cgProjectDaoMap.get(projectBean);
    }

    @Override
    public DaoInterface getDao(ProjectBean projectBean) {
        return (DaoInterface) cgProjectDaoMap.get(projectBean);
    }

}
