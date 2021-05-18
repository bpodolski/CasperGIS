/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class MapItemsGetter {
     public abstract List<MapElementBean> getMapItems(MapBean mapBean);

    public static MapItemsGetter getDefault() {
        MapItemsGetter mapItemsGetter = Lookup.getDefault().lookup(MapItemsGetter.class);
        if (mapItemsGetter == null) {
            mapItemsGetter = new DefaultMapItemsGetter();        }
        return mapItemsGetter;
    }

    private static class DefaultMapItemsGetter extends MapItemsGetter {

        @Override
        public List<MapElementBean> getMapItems(MapBean mapBean) {
            ArrayList<MapElementBean> mapItemsList = new <PrintoutBean>ArrayList();
            for (int i = 1; i < 2; i++) {
                mapItemsList.add(new MapElementBean(null, i + ".TestLayer - DefImpl"));
            }
            return mapItemsList;
        }
    }
}
