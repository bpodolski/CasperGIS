/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelMapitemsList;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import java.util.ArrayList;
import java.util.List;
import org.openide.explorer.ExplorerManager;
import org.openide.util.Lookup;

/**
 * Abstract class - The base for the service that provides the list of maps in
 * project(project described by ProjectBean)
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class MapListMgr {

    public abstract void initModel(ProjectBean projectBean);
    
    public abstract ModelMapsList getModel(ProjectBean projectBean);

    /**
     * Base function of service - get the list of maps in project
     *
     * @param projectBean
     * @return list of maps
     */
    public abstract List<MapBean> getMapList(ProjectBean projectBean);
    

    /**
     * Add map
     *
     *
     * @param mapBean - map to add
     * @param projectBean - project with maps
     */
    public abstract void add(MapBean mapBean, ProjectBean projectBean);

    /**
     * Delete map
     *
     *
     * @param mapBean
     */
    public abstract void delete(MapBean mapBean, ProjectBean projectBean);

    /**
     * Close map
     *
     * @param mapBean
     */
    public abstract void close(MapBean mapBean, ProjectBean projectBean);

    /**
     * Update map
     *
     * @param mapBean
     */
    public abstract void update(MapBean mapBean, ProjectBean projectBean);

    public static MapListMgr getDefault() {
        MapListMgr service = Lookup.getDefault().lookup(MapListMgr.class);
        if (service == null) {
            service = new DefaultMapListMgr();
        }
        return service;
    }

    private static class DefaultMapListMgr extends MapListMgr {

        MapBean mapBean;
        ModelMapitemsList model;
        ProjectBean projectBean;

        public DefaultMapListMgr() {
        }

    
        @Override
        public List<MapBean> getMapList(ProjectBean projectBean) {
            ArrayList<MapBean> mapList = new <MapBean>ArrayList();
            for (int i = 1; i < 4; i++) {
                mapList.add(new MapBean(null, i + ".TestMap - DefImpl"));
            }
            return mapList;
        }

        @Override
        public void add(MapBean mapBean, ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void delete(MapBean mapBean, ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void close(MapBean mapBean, ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void update(MapBean mapBean, ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void initModel(ProjectBean projectBean) {
            this.projectBean = projectBean;
        }

        @Override
        public ModelMapsList getModel(ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
