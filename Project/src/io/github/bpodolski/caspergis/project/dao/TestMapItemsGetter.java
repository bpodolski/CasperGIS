/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.dao;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.services.MapItemsGetter;
import java.util.ArrayList;
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
        for (int i = 1; i < 10; i++) {
            mapItemsList.add(new LayerBean(null, i + ".TestLayer"));
        }
        return mapItemsList;
    }

}
