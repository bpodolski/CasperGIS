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
import io.github.bpodolski.caspergis.services.ProjectMgr;
import java.io.File;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = ProjectMgr.class, path = "Project")
public class ProjectInfoMgr extends ProjectMgr {

    @Override
    public boolean setupProjectInfo(ProjectBean projectBean) {

        File fProject = new File(projectBean.getPath());
        if (!fProject.exists()) {
            ProjectDAO.createDb(projectBean.getPath());
        }

        ProjectDAO dao = (ProjectDAO) cgProjectDaoMap.get(projectBean);
        if (dao == null) {
            dao = new ProjectDAO(projectBean.getPath(), false);
            cgProjectDaoMap.put(projectBean, dao);
        }

        CgProjectInfo pi = dao.getProjectInfo();
        pi.setName(projectBean.getName());
        pi.setPath(projectBean.getPath());
        dao.saveProjectInfo(pi);

        return true;
    }

    @Override
    public void updateProjectInfo(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
