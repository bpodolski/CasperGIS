/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.project.CgRegistryProject;
import static io.github.bpodolski.caspergis.project.CgRegistryProject.cgProjectDaoMap;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.ProjectInfoService;
import io.github.bpodolski.caspergis.services.ProjectListService;
import io.github.bpodolski.caspergis.utils.CgUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openide.util.Lookup;
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
        if (!fProject.exists()) {
            return false;
        }

        CgUtils.io.getOut().println("CgProjectInfoService.setupProjectInfo - projectBean.path: " + fProject.getPath());

        ProjectDAO dao = (ProjectDAO) cgProjectDaoMap.get(projectBean);
        if (dao == null) {
            dao = new ProjectDAO(projectBean.getPath(), false);
            cgProjectDaoMap.put(projectBean, dao);
        }

        String sName = dao.getProjectInfo().getName();
        if (sName.equals("")) {
            sName = fProject.getName().replace(".cgpr", "");
        }
        projectBean.setName(sName);
        return true;
    }

    @Override
    public void updateProjectInfo(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MapBean> getMapList(ProjectBean projectBean) {

        ProjectDAO projectDAO = (ProjectDAO) cgProjectDaoMap.get(projectBean);
        if (projectDAO == null) {
            return null;
        }
        ArrayList<MapBean> mapList = new <MapBean>ArrayList();

        Iterator<CgMap> itr = projectDAO.getCgMaps().iterator();
        while (itr.hasNext()) {
            CgMap cgMap = itr.next();
            MapBean mb = new MapBean(null, cgMap.getName());

            CgRegistryProject.cgMapMap.put(mb, cgMap);
            CgRegistryProject.cgMapDaoMap.put(mb, projectDAO);

            mb.setActive(cgMap.isDefault_map());
            mapList.add(mb);
        }
        return mapList;
    }

    @Override
    public List<PrintoutBean> gePrintoutList(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProjectBean> getProjectList() {
        List<ProjectBean> listP = new ArrayList<>();
        ProjectListService projectService = Lookup.getDefault().lookup(ProjectListService.class);

        //add temp project at first
        listP.add(projectService.getSystemProject());
        //add registered projects
        listP.addAll(projectService.getProjectList());

        Iterator<ProjectBean> itr = listP.iterator();

        while (itr.hasNext()) {
            setupProjectInfo(itr.next());
        }
        return listP;
    }

}
