/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.dao;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.MapGetter;
import java.util.ArrayList;
import java.util.Iterator;
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

        JpaProjectDbDAO projectDbDAO = new JpaProjectDbDAO(projectBean.getPath());
        Iterator<CgMap> itr = projectDbDAO.getCgMaps().iterator();
        while (itr.hasNext()) {
            CgMap cgMap = itr.next();
            MapBean mb = new MapBean(null, cgMap.getName());
            mapList.add(mb);
        }
        return mapList;
    }

}
