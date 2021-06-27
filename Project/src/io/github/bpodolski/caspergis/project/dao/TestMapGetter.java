/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.dao;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.MapGetter;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = MapGetter.class)
public class TestMapGetter extends MapGetter {

    @Override
    public List<MapBean> getMapList(ProjectBean projectBean) {
        ArrayList<MapBean> mapList = new <MapBean>ArrayList();
        for (int i = 1; i < 4; i++) {
            MapBean mapBean;
            if (projectBean.isActive() && i == 1) {
                mapBean = new MapBean(null, i + ".TestActiveMap");
                mapBean.setActive(true); 
                
            } else {
                mapBean = new MapBean(null, i + ".TestMap");
            }
            mapList.add(mapBean);
        }

        projectBean.setListMapBean(mapList);
        return mapList;
    }

}
