/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import static io.github.bpodolski.caspergis.project.CgRegistryProject.cgProjectDaoMap;
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
public class CgProjectListService  extends ServiceProjectManager {

    @Override
    public ProjectBean getSystemProject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getServiceName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProjectBean> getProjectList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

//        ProjectDAO.createDb(projectBean.getPath());
        
        ProjectDAO dao = new ProjectDAO(projectBean.getPath(), true);//(ProjectDAO) cgProjectDaoMap.get(projectBean);
        cgProjectDaoMap.put(projectBean, dao);


        CgProjectInfo pi = dao.getProjectInfo();
        pi.setName(projectBean.getName());
        pi.setPath(projectBean.getPath());
        pi.setDescription(projectBean.getDescription());
        dao.saveProjectInfo(pi);
//        dao.dispose();
    }
    
}
