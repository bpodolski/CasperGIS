/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.project.CgRegistryProject;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgLayer;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.MapItemsGetter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = MapItemsGetter.class)
public class TestMapItemsGetter extends MapItemsGetter {

    @Override
    public List<MapElementBean> getMapItems(MapBean mapBean) {
        ArrayList<MapElementBean> mapItemsList = new <MapElementBean>ArrayList();
        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);
        if (cgMap == null) {
            return mapItemsList;
        }

        ProjectDAO dao = (ProjectDAO) CgRegistryProject.cgMapDaoMap.get(mapBean);
                
        Iterator<CgLayer> itr = dao.getLayers(cgMap).iterator();//cgMap.getLayers().iterator();
        
        while (itr.hasNext()) {
            CgLayer cgl = itr.next();
            LayerBean lb = new LayerBean(null, cgl.getName());
            
            CgRegistryProject.cgLayerMap.put(lb, cgl);
            mapItemsList.add(lb);
        }

        return mapItemsList;
    }

}
