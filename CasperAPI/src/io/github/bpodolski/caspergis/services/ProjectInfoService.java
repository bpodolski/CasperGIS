/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 * Abstract class - The base for the service that provides the list of maps in
 * project
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class ProjectInfoService {

    /**
     * Get first default temporary project with registered in system data base project paths in order based on field
     * POSITION, projects list obtained from ProjectListService
     *
     * @return
     */
    public abstract List<ProjectBean> getProjectList();

    /**
     *
     * @param projectBean
     * @return
     */
    public abstract boolean setupProjectInfo(ProjectBean projectBean);

    public abstract void updateProjectInfo(ProjectBean projectBean);

    /**
     * Base function of service - get the list of maps in project
     *
     * @param projectBean
     * @return list of maps
     */
    public abstract List<MapBean> getMapList(ProjectBean projectBean);

    /**
     * Base function of service - get the list of printouts in project
     *
     * @param projectBean
     * @return list of maps
     */
    public abstract List<PrintoutBean> gePrintoutList(ProjectBean projectBean);

    public static ProjectInfoService getDefault() {
        ProjectInfoService mapGetterService = Lookup.getDefault().lookup(ProjectInfoService.class);
        if (mapGetterService == null) {
            mapGetterService = new DefaultProjectInfoService();
        }
        return mapGetterService;
    }

    private static class DefaultProjectInfoService extends ProjectInfoService {

        @Override
        public List<MapBean> getMapList(ProjectBean projectBean) {
            ArrayList<MapBean> mapList = new <MapBean>ArrayList();
            for (int i = 1; i < 4; i++) {
                mapList.add(new MapBean(null, i + ".TestMap - DefImpl"));
            }
            return mapList;
        }

        @Override
        public List<PrintoutBean> gePrintoutList(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean setupProjectInfo(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void updateProjectInfo(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<ProjectBean> getProjectList() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
