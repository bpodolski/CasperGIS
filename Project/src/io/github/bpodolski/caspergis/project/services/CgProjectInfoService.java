/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import static io.github.bpodolski.caspergis.project.CgRegistryProject.cgProjectDaoMap;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.services.ProjectInfoService;
import java.io.File;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = ProjectInfoService.class)
public class CgProjectInfoService extends ProjectInfoService {

    @Override
    public boolean setupProjectInfo(ProjectBean projectBean) {

        File fProject = new File(projectBean.getPath());
        if (!fProject.exists()) return false;
        
        ProjectDAO dao = (ProjectDAO) cgProjectDaoMap.get(projectBean);
        if (dao != null) {
            String sName = dao.getProjectInfo().getName();
            if (sName.equals("")) sName = fProject.getName();
            projectBean.setName(sName);
            
        } else {

        }
        return true;
    }

    @Override
    public void updateProjectInfo(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MapBean> getMapList(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrintoutBean> gePrintoutList(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProjectBean> getProjectList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
