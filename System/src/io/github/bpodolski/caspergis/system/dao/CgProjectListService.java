/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.dao;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.ProjectListService;
import io.github.bpodolski.caspergis.system.CgRegistrySystem;
import io.github.bpodolski.caspergis.system.datamodel.CgProject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = ProjectListService.class, path = "system")
public class CgProjectListService extends ProjectListService {

    JpaSystemDbDAO dao = new JpaSystemDbDAO();

    @Override
    public ProjectBean getSystemProject() {
        ProjectBean systemProject = new ProjectBean();
        systemProject.setName("SystemTestProject");
        systemProject.setPath(NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_DEFAULT_PROJECT_PATH, ""));
        return systemProject;
    }

    @Override
    public List<ProjectBean> getProjectList() {
        ArrayList<ProjectBean> projectList = new ArrayList<ProjectBean>();
        Iterator<CgProject> itr = dao.getProjects().iterator();
        while (itr.hasNext()) {
            CgProject cgProject = itr.next();
            ProjectBean projectBean = new ProjectBean();

            projectBean.setName(cgProject.getName());
            projectBean.setPath(cgProject.getPath());
            projectList.add(projectBean);

            CgRegistrySystem.projectMap.put(projectBean, itr);

        }

        return projectList;
    }

    @Override
    public void delete(ProjectBean projectBean) {
        CgProject project = (CgProject) CgRegistrySystem.projectMap.get(projectBean);
        dao.deleteProject(project);
    }

    @Override
    public void update(ProjectBean projectBean) {
        CgProject project = (CgProject) CgRegistrySystem.projectMap.get(projectBean);
        dao.saveProject(project);
    }

    @Override
    public void add(ProjectBean projectBean) {

        CgProject project = new CgProject();
        project.setName(projectBean.getName());
        project.setPath(projectBean.getPath());

        dao.saveProject(project);
        CgRegistrySystem.projectMap.put(projectBean, project);
    }

    @Override
    public void close(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
