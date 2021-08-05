/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.project.CgRegistryProject;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgProjectInfo;
import io.github.bpodolski.caspergis.services.ServiceProjectManager;
import java.io.File;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = ServiceProjectManager.class, path = "Project")
public class CgProjectListService extends ServiceProjectManager {

//    private static final HashMap projectDaoMap = new HashMap<ProjectBean, ProjectDAO>();
    
    
    @Override
    public ProjectBean getSystemProject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getServiceName() {
        return this.getClass().getName();
    }

    @Override
    public List<ProjectBean> getProjectList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ProjectBean projectBean) {
        close(projectBean);
        File f = new File(projectBean.getPath());
        if (f.exists()) f.delete();
    }

    @Override
    public void close(ProjectBean projectBean) {
        ProjectDAO dao = (ProjectDAO) CgRegistryProject.cgProjectDaoMap.get(projectBean);
        if (dao != null)  dao.dispose();
    }

    @Override
    public void update(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(ProjectBean projectBean) {
        File fProject = new File(projectBean.getPath());
        if (!fProject.exists()) {
            fProject.delete();
        }

        ProjectDAO dao = new ProjectDAO(projectBean.getPath(), true);//(ProjectDAO) cgProjectDaoMap.get(projectBean);
        CgRegistryProject.cgProjectDaoMap.put(projectBean, dao);

        CgProjectInfo pi = dao.getProjectInfo();
        pi.setName(projectBean.getName());
        pi.setPath(projectBean.getPath());
        pi.setDescription(projectBean.getDescription());
        dao.saveProjectInfo(pi);

    }

}
