/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ElementMapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 * Abstract class - The base for the service that provides the list of maps items in map of project
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class MapItemsGetter {

    /**
     * Get list of map items like layers or groups
     * @param mapBean
     * @return
     */
    public abstract List<ElementMapBean> getMapItems(MapBean mapBean);

    /**
     * Get default getter when other are unavaible
     * @return
     */
    public static MapItemsGetter getDefault() {
        MapItemsGetter mapItemsGetter = Lookup.getDefault().lookup(MapItemsGetter.class);
        if (mapItemsGetter == null) {
            mapItemsGetter = new DefaultMapItemsGetter();        }
        return mapItemsGetter;
    }

    private static class DefaultMapItemsGetter extends MapItemsGetter {

        @Override
        public List<ElementMapBean> getMapItems(MapBean mapBean) {
            ArrayList<ElementMapBean> mapItemsList = new <PrintoutBean>ArrayList();
            for (int i = 1; i < 10; i++) {
                mapItemsList.add(new ElementMapBean(null, i + ".TestLayer - DefImpl"));
            }
            return mapItemsList;
        }
    }
}
