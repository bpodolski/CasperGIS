/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class ProjectGetter {

    public abstract ProjectBean getSystemProject();

    public abstract List<ProjectBean> getProjectList();
    //    public abstract JpaProjectDbDAO getProjectDbDAO(ProjectBean projectBean);

    public static ProjectGetter getDefault() {
        ProjectGetter projectGetterService = Lookup.getDefault().lookup(ProjectGetter.class);
        if (projectGetterService == null) {
            projectGetterService = new DefaultProjectGetter();
        }
        return projectGetterService;
    }

    private static class DefaultProjectGetter extends ProjectGetter {

        @Override
        public ProjectBean getSystemProject() {
            ProjectBean systemProject = new ProjectBean("SystemTestProject");
            return systemProject;
        }

        @Override
        public List<ProjectBean> getProjectList() {
            ArrayList<ProjectBean> projectList = new <ProjectBean>ArrayList();
            for (int i=1;i<4;i++){projectList.add(new ProjectBean(i+".TestProject"));}
            return projectList;
        }

    }

}
