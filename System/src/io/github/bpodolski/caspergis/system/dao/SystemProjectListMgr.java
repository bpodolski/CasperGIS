/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.dao;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.ProjectListMgr;
import io.github.bpodolski.caspergis.system.CgRegistrySystem;
import io.github.bpodolski.caspergis.system.datamodel.CgSysProject;
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
@ServiceProvider(service = ProjectListMgr.class, path = "System")
public class SystemProjectListMgr extends ProjectListMgr {

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
        Iterator<CgSysProject> itr = daoSystem.getProjects().iterator();
        while (itr.hasNext()) {
            CgSysProject cgSysProject = itr.next();
            ProjectBean projectBean = new ProjectBean();
            
            projectBean.setPath(cgSysProject.getPath());
            projectBean.setPosition(cgSysProject.getPosition());
            projectBean.setHidden(cgSysProject.isHidden());
            
            projectList.add(projectBean);

            CgRegistrySystem.projectMap.put(projectBean, cgSysProject);

        }

        return projectList;
    }

    @Override
    public void delete(ProjectBean projectBean) {
        close(projectBean);
    }

    @Override
    public void update(ProjectBean projectBean) {
        CgSysProject project = (CgSysProject) CgRegistrySystem.projectMap.get(projectBean);
        daoSystem.saveProject(project);
    }

    @Override
    public void add(ProjectBean projectBean) {

        CgSysProject cgSysProject = new CgSysProject();
        
        cgSysProject.setPath(projectBean.getPath());
        cgSysProject.setPosition(projectBean.getPosition());
        cgSysProject.setHidden(projectBean.isHidden());

        daoSystem.saveProject(cgSysProject);
        CgRegistrySystem.projectMap.put(projectBean, cgSysProject);

        Collection<? extends ProjectListMgr> srvList = Lookups.forPath("Project").lookupAll(ProjectListMgr.class);
        ProjectListMgr projectListService = srvList.iterator().next();
        projectListService.add(projectBean);

    }

    @Override
    public void close(ProjectBean projectBean) {
        if (CgRegistrySystem.projectMap.containsKey(projectBean)) {
            CgSysProject project = (CgSysProject) CgRegistrySystem.projectMap.get(projectBean);
            daoSystem.deleteProject(project);
            CgUtils.io.getOut().println(projectBean.getName()+" deleted");
        } else {
            CgUtils.io.getOut().println(projectBean.getName()+" can't delete");            
        }
    }

}
