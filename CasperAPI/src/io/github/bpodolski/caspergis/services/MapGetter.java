/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class MapGetter {

    public abstract List<MapBean> getMapList(ProjectBean projectBean);

    public static MapGetter getDefault() {
        MapGetter mapGetterService = Lookup.getDefault().lookup(MapGetter.class);
        if (mapGetterService == null) {
            mapGetterService = new DefaultMapGetter();
        }
        return mapGetterService;
    }

    private static class DefaultMapGetter extends MapGetter {

        @Override
        public List<MapBean> getMapList(ProjectBean projectBean) {
            ArrayList<MapBean> mapList = new <MapBean>ArrayList();
            for (int i = 1; i < 4; i++) {
                mapList.add(new MapBean(null, i + ".TestMap - DefImpl"));
            }
            return mapList;
        }
    }
}
