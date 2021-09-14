/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.project.CgRegistryProject;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgLayer;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.MapObjectMgr;
import io.github.bpodolski.caspergis.services.MapitemListMgr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = MapitemListMgr.class, path = "Project")
public class ProjectMapitemListMgr extends MapitemListMgr {

    @Override
    public List<MapitemBean> getMapItemsList(MapBean mapBean) {
        ArrayList<MapitemBean> mapItemsList = new <MapitemBean>ArrayList();
        
//        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);
//        
//        if (cgMap == null) {
//            return mapItemsList;
//        }
//
//        MapObjectMgr mapObjectMgr = Lookups.forPath("Project").lookupAll(MapObjectMgr.class).iterator().next();
//        ProjectDAO dao = (ProjectDAO) mapObjectMgr.getDao(mapBean);
//                
//        Iterator<CgLayer> itr = dao.getLayers(cgMap).iterator();//cgMap.getLayers().iterator();
//        
//        while (itr.hasNext()) {
//            CgLayer cgl = itr.next();
//            LayerBean lb = new LayerBean(null, cgl.getName());
//            
//            CgRegistryProject.cgLayerMap.put(lb, cgl);
//            mapItemsList.add(lb);
//        }

        return mapItemsList;
    }

    @Override
    public void add(MapBean mapBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MapBean mapBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close(MapBean mapBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
