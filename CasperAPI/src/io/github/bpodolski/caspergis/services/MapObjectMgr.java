/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.interfaces.DaoInterface;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class MapObjectMgr {

    /**
     *
     * @param mapBean
     * @param projectBean
     * @return true jeśli utworzenie projectDAO się powiodło
     */
    public abstract boolean setupMapInfo(MapBean mapBean, ProjectBean projectBean);

    public abstract void updateMapInfo(MapBean mapBean);

    public abstract DaoInterface getDao(MapBean mapBean);

    public static MapObjectMgr getDefault() {
        MapObjectMgr service = Lookup.getDefault().lookup(MapObjectMgr.class);
        if (service == null) {
            service = new MapObjectMgr.DefaultMapObjectMgr();
        }
        return service;
    }
    
    
    private static class DefaultMapObjectMgr extends MapObjectMgr {

        @Override
        public boolean setupMapInfo(MapBean mapBean, ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void updateMapInfo(MapBean mapBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DaoInterface getDao(MapBean mapBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

      
    }
}
