/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import io.github.bpodolski.caspergis.project.CgRegistryProject;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.MapListMgr;
import io.github.bpodolski.caspergis.services.ProjectObjectMgr;
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

    @Override
    public void initModel(ProjectBean projectBean) {
        ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
        ProjectDAO projectDAO = (ProjectDAO) projectObjectMgr.getDao(projectBean);

        Iterator<CgMap> itr = projectDAO.getCgMaps().iterator();
        while (itr.hasNext()) {
            CgMap cgMap = itr.next();
            MapBean mb = new MapBean(null, cgMap.getName());
            ModelMapsList modelMapsList = new ModelMapsList();

            CgRegistryProject.cgMapMap.put(mb, cgMap);
            CgRegistryProject.cgMapDaoMap.put(mb, projectDAO);
            CgRegistryProject.cgMapModelMap.put(projectBean, modelMapsList);

            mb.setActive(cgMap.isDefault_map());
            modelMapsList.add(mb);
        }

    }

    @Override
    public List<MapBean> getMapList(ProjectBean projectBean) {
        ModelMapsList modelMapsList = (ModelMapsList) CgRegistryProject.cgMapModelMap.get(projectBean);
        return (List<MapBean>) modelMapsList.list();
    }

    @Override
    public void add(MapBean mapBean, ProjectBean projectBean) {
        ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
        ProjectDAO projectDAO = (ProjectDAO) projectObjectMgr.getDao(projectBean);

        CgMap cgMap = projectDAO.addMap(mapBean.getName());
        ModelMapsList modelMapsList = new ModelMapsList();

        CgRegistryProject.cgMapMap.put(mapBean, cgMap);
        CgRegistryProject.cgMapDaoMap.put(mapBean, projectDAO);
        CgRegistryProject.cgMapModelMap.put(projectBean, modelMapsList);

    }

    @Override
    public void delete(MapBean mapBean, ProjectBean projectBean) {
        close(mapBean, projectBean);

    }

    @Override
    public void close(MapBean mapBean, ProjectBean projectBean) {
        ProjectDAO projectDAO = (ProjectDAO) CgRegistryProject.cgMapDaoMap.get(mapBean);
        ModelMapsList modelMapsList = (ModelMapsList) CgRegistryProject.cgMapModelMap.get(projectBean);

        modelMapsList.remove(mapBean);
        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);

        projectDAO.deleteMap(cgMap);

    }

    @Override
    public void update(MapBean mapBean, ProjectBean projectBean) {
        ProjectDAO projectDAO = (ProjectDAO) CgRegistryProject.cgMapDaoMap.get(mapBean);
        ModelMapsList modelMapsList = (ModelMapsList) CgRegistryProject.cgMapModelMap.get(projectBean);

//        modelMapsList.notify();
        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);

        projectDAO.saveMap(cgMap);

    }

    @Override
    public ModelMapsList getModel(ProjectBean projectBean) {
        return (ModelMapsList) CgRegistryProject.cgMapModelMap.get(projectBean);
    }

}
