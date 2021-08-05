/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.dao;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.ServiceProjectManager;
import io.github.bpodolski.caspergis.system.CgRegistrySystem;
import io.github.bpodolski.caspergis.system.datamodel.CgProject;
import io.github.bpodolski.caspergis.utils.CgUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = ServiceProjectManager.class, path = "System")
public class CgProjectListService extends ServiceProjectManager {

    JpaSystemDbDAO daoSystem = new JpaSystemDbDAO();

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
        Iterator<CgProject> itr = daoSystem.getProjects().iterator();
        while (itr.hasNext()) {
            CgProject cgProject = itr.next();
            ProjectBean projectBean = new ProjectBean();

            projectBean.setName(cgProject.getName());
            projectBean.setPath(cgProject.getPath());
            projectList.add(projectBean);

            CgRegistrySystem.projectMap.put(projectBean, cgProject);

        }

        return projectList;
    }

    @Override
    public void delete(ProjectBean projectBean) {
        close(projectBean);
    }

    @Override
    public void update(ProjectBean projectBean) {
        CgProject project = (CgProject) CgRegistrySystem.projectMap.get(projectBean);
        daoSystem.saveProject(project);
    }

    @Override
    public void add(ProjectBean projectBean) {

        CgProject project = new CgProject();
        project.setName(projectBean.getName());
        project.setPath(projectBean.getPath());

        daoSystem.saveProject(project);
        CgRegistrySystem.projectMap.put(projectBean, project);

        Collection<? extends ServiceProjectManager> srvList = Lookups.forPath("Project").lookupAll(ServiceProjectManager.class);
        ServiceProjectManager projectListService = srvList.iterator().next();
        projectListService.add(projectBean);

    }

    @Override
    public void close(ProjectBean projectBean) {
        if (CgRegistrySystem.projectMap.containsKey(projectBean)) {
            CgProject project = (CgProject) CgRegistrySystem.projectMap.get(projectBean);
            daoSystem.deleteProject(project);
            CgUtils.io.getOut().println(projectBean.getName()+" deleted");
        } else {
            CgUtils.io.getOut().println(projectBean.getName()+" can't delete");            
        }
    }

    @Override
    public String getServiceName() {
        return "CgProjectListService";
    }

}
