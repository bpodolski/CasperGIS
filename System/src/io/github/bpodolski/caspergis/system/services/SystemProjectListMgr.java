/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.services;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import io.github.bpodolski.caspergis.services.ProjectObjectMgr;
import io.github.bpodolski.caspergis.system.CgRegistrySystem;
import io.github.bpodolski.caspergis.system.dao.JpaSystemDbDAO;
import io.github.bpodolski.caspergis.system.datamodel.CgSysProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.Lookups;

//TODO: klasa do usunięcia
public class SystemProjectListMgr{

    private final JpaSystemDbDAO daoSystem = new JpaSystemDbDAO();
    private ProjectObjectMgr projectObjectMgr;
    
    private ModelProjectList model;
    private final HashMap projectMap = new HashMap<ProjectBean, CgSysProject>();

    public void initModel() {
        projectObjectMgr =  Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
        
        List<ProjectBean> projectList = new ArrayList();
        projectList.add(getSystemProject());
        projectList.addAll(getProjectList());
        model = new ModelProjectList(projectList);
        
        
    }


    public ModelProjectList getModel() {
        return model;
    }

    public ProjectBean getSystemProject() {
        ProjectBean systemProject = new ProjectBean();
        systemProject.setName("SystemTestProject");
        systemProject.setPath(NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_DEFAULT_PROJECT_PATH, ""));
        return systemProject;
    }

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
            
            projectMap.put(projectBean, cgSysProject);
            CgRegistrySystem.projectMap.put(projectBean, cgSysProject);

        }

        return projectList;
    }

//    @Override
//    public void delete(ProjectBean projectBean) {
//        close(projectBean);
//    }
//
//    @Override
//    public void update(ProjectBean projectBean) {
//        CgSysProject project = (CgSysProject) CgRegistrySystem.projectMap.get(projectBean);
//        daoSystem.saveProject(project);
//    }
//
//    @Override
//    public void add(ProjectBean projectBean) {
//
//        CgSysProject cgSysProject = new CgSysProject();
//        
//        cgSysProject.setPath(projectBean.getPath());
//        cgSysProject.setPosition(projectBean.getPosition());
//        cgSysProject.setHidden(projectBean.isHidden());
//
//        daoSystem.saveProject(cgSysProject);
//        CgRegistrySystem.projectMap.put(projectBean, cgSysProject);
//
//        Collection<? extends ProjectListMgr> srvList = Lookups.forPath("Project").lookupAll(ProjectListMgr.class);
//        ProjectListMgr projectListService = srvList.iterator().next();
//        projectListService.add(projectBean);
//
//    }
//
//    @Override
//    public void close(ProjectBean projectBean) {
//        if (CgRegistrySystem.projectMap.containsKey(projectBean)) {
//            CgSysProject project = (CgSysProject) CgRegistrySystem.projectMap.get(projectBean);
//            daoSystem.deleteProject(project);
//            CgUtils.io.getOut().println(projectBean.getName()+" deleted");
//        } else {
//            CgUtils.io.getOut().println(projectBean.getName()+" can't delete");            
//        }
//    }
}
