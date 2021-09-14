/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelMapitemsList;
import io.github.bpodolski.caspergis.project.CgRegistryProject;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.MapListMgr;
import io.github.bpodolski.caspergis.services.ProjectObjectMgr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = MapListMgr.class, path = "Project")
public class ProjectMapListMgr extends MapListMgr {

    private final HashMap mapModelElementsManagers = new HashMap<MapBean, ModelMapitemsList>();
    private final HashMap mapDaoMap = new HashMap<MapBean, ProjectDAO>();
    private final HashMap mapMap = new HashMap<MapBean, CgMap>();

    @Override
    public void initModel(ProjectBean projectBean) {
        ArrayList<MapBean> mapList = new <MapBean>ArrayList();

        ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
        ProjectDAO projectDAO = (ProjectDAO) projectObjectMgr.getDao(projectBean);

        Iterator<CgMap> itr = projectDAO.getCgMaps().iterator();
        while (itr.hasNext()) {
            CgMap cgMap = itr.next();
            MapBean mb = new MapBean(null, cgMap.getName());

            mapMap.put(mb, cgMap);
            mapDaoMap.put(mb, projectDAO);

            mb.setActive(cgMap.isDefault_map());
            mapList.add(mb);
        }

    }

    @Override
    public List<MapBean> getMapList(ProjectBean projectBean) {
        ArrayList<MapBean> mapList = new <MapBean>ArrayList();

        ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
        ProjectDAO projectDAO = (ProjectDAO) projectObjectMgr.getDao(projectBean);

        Iterator<CgMap> itr = projectDAO.getCgMaps().iterator();
        while (itr.hasNext()) {
            CgMap cgMap = itr.next();
            MapBean mb = new MapBean(null, cgMap.getName());

//            CgRegistryProject.cgMapMap.put(mb, cgMap);
//            CgRegistryProject.cgMapDaoMap.put(mb, projectDAO);

            mb.setActive(cgMap.isDefault_map());
            mapList.add(mb);
        }
        return mapList;
    }

    @Override
    public void add(MapBean mapBean, ProjectBean projectBean) {
        ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
        ProjectDAO projectDAO = (ProjectDAO) projectObjectMgr.getDao(projectBean);

        CgMap cgMap = projectDAO.addMap(mapBean.getName());

//        CgRegistryProject.cgMapMap.put(mapBean, cgMap);
//        CgRegistryProject.cgMapDaoMap.put(mapBean, projectDAO);

    }

    @Override
    public void delete(MapBean mapBean) {
//        ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
//        ProjectDAO projectDAO = (ProjectDAO) projectObjectMgr.getDao(projectBean);
//        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);

    }

    @Override
    public void close(MapBean mapBean) {
//        ProjectDAO projectDAO = (ProjectDAO) CgRegistryProject.cgMapDaoMap.get(mapBean);
//        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);
    }

    @Override
    public void update(MapBean mapBean) {
//        ProjectDAO projectDAO = (ProjectDAO) CgRegistryProject.cgMapDaoMap.get(mapBean);
//        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);
//
//        projectDAO.saveMap(cgMap);

    }

}
