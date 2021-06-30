/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.dao;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.ProjectGetter;
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
@ServiceProvider(service = ProjectGetter.class)
public class TestProjectGetter extends ProjectGetter {

    @Override
    public ProjectBean getSystemProject() {
        ProjectBean systemProject = new ProjectBean("SystemTestProject");
        systemProject.setActive(true);
        systemProject.setPath(NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_DEFAULT_PROJECT_PATH, ""));
        return systemProject;
    }

    @Override
    public List<ProjectBean> getProjectList() {
        ArrayList<ProjectBean> projectList = new ArrayList<ProjectBean>();

        JpaSystemDbDAO dao = new JpaSystemDbDAO();
        Iterator<CgProject> itr = dao.getProjects().iterator();
        while (itr.hasNext()) {
            CgProject cgProject = itr.next();
            ProjectBean projectBean = new ProjectBean(cgProject.getName());
            projectBean.setPath(cgProject.getPath());
            projectList.add(projectBean);
        }

        return projectList;
    }

}
