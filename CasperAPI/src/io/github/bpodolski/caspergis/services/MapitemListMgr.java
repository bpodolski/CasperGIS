/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.model.ModelMapitemsList;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 * Abstract class - The base for the service that provides the list of maps
 * items in map of project
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class MapitemListMgr {

    public abstract void initModel(MapBean mapBean);

    public abstract ModelMapitemsList getModel(MapBean mapBean);

    public abstract boolean isModelReady();

    /**
     * Get list of map items like layers or groups
     *
     * @param mapBean
     * @return
     */
    public abstract List<MapitemBean> getMapItemsList(MapBean mapBean);

    public abstract void add(LayerBean layerBean, MapBean mapBean);

    public abstract void update(LayerBean layerBean);

    public abstract void close(LayerBean layerBean);

    /**
     * Get default getter when other are unavaible
     *
     * @return
     */
    public static MapitemListMgr getDefault() {
        MapitemListMgr mapItemsGetter = Lookup.getDefault().lookup(MapitemListMgr.class);
        if (mapItemsGetter == null) {
            mapItemsGetter = new DefaultMapItemsGetter();
        }
        return mapItemsGetter;
    }

    private static class DefaultMapItemsGetter extends MapitemListMgr {

        @Override
        public List<MapitemBean> getMapItemsList(MapBean mapBean) {
            ArrayList<MapitemBean> mapItemsList = new <PrintoutBean>ArrayList();
            for (int i = 1; i < 10; i++) {
                mapItemsList.add(new MapitemBean(null, i + ".TestLayer - DefImpl"));
            }
            return mapItemsList;
        }

        @Override
        public void add(LayerBean layerBean, MapBean mapBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void update(LayerBean layerBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void close(LayerBean layerBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void initModel(MapBean mapBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ModelMapitemsList getModel(MapBean mapBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isModelReady() {
            return false;
        }
    }
}
