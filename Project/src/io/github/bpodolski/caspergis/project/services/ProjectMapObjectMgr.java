/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.interfaces.DaoInterface;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.MapListMgr;
import io.github.bpodolski.caspergis.services.MapObjectMgr;
import io.github.bpodolski.caspergis.services.MapitemListMgr;
import io.github.bpodolski.caspergis.services.ProjectObjectMgr;
import java.util.HashMap;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author bpodolski
 */
@ServiceProvider(service = MapObjectMgr.class, path = "Project")
public class ProjectMapObjectMgr extends MapObjectMgr {

    private final HashMap cgMapDaoMap = new HashMap<MapBean, ProjectDAO>();
    

    @Override
    public boolean setupMapInfo(MapBean mapBean, ProjectBean projectBean) {

        ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
        ProjectDAO projectDAO = (ProjectDAO) projectObjectMgr.getDao(projectBean);
        cgMapDaoMap.put(mapBean, projectDAO);
        
        return true;
    }

    @Override
    public void updateMapInfo(MapBean mapBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DaoInterface getDao(MapBean mapBean) {
        return (DaoInterface) cgMapDaoMap.get(mapBean);
    }

}
