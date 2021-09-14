/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.model.ModelMapitemsList;
import javax.swing.event.ChangeListener;
import org.openide.explorer.ExplorerManager;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class MapExplorerManagerMgr {

    public abstract void setActiveMapBean(MapBean activeMapBean);

    public abstract MapBean getActiveMapBean();

    public abstract void clearActiveMapBean();

    public abstract void addMapExplorerManager(MapBean mapBean, ExplorerManager mgr);

    public abstract void addMapExplorerManager(MapBean mapBean);

    public abstract ExplorerManager getMapExplorerManager(MapBean mapBean);

    public abstract void deleteMapExplorerManager(MapBean mapBean);
    
    public abstract void addChangeListener(ChangeListener l);

    public abstract void removeChangeListener(ChangeListener l);

    public static MapExplorerManagerMgr getDefault() {
        MapExplorerManagerMgr service = Lookup.getDefault().lookup(MapExplorerManagerMgr.class);
        if (service == null) {
            service = new DefaultMapExplorerManagerMgr();
        }
        return service;
    }

    private static class DefaultMapExplorerManagerMgr extends MapExplorerManagerMgr {

        MapBean mapBean;
        ExplorerManager mgr;
        ModelMapitemsList model;

        public DefaultMapExplorerManagerMgr() {
            this.mapBean = new MapBean(null, "[No active map]");
            this.mgr = new ExplorerManager();
            this.model = new ModelMapitemsList();
        }

        @Override
        public void setActiveMapBean(MapBean activeMapBean) {
            this.mapBean = activeMapBean;

        }

        @Override
        public MapBean getActiveMapBean() {
            return this.mapBean;
        }

        @Override
        public void clearActiveMapBean() {
            this.mapBean = new MapBean(null, "[No active map]");
        }

        @Override
        public void addMapExplorerManager(MapBean mapBean, ExplorerManager mgr) {
            this.mgr = mgr;
            this.mapBean = mapBean;
        }

        @Override
        public void addMapExplorerManager(MapBean mapBean) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ExplorerManager getMapExplorerManager(MapBean mapBean) {
            return mgr;
        }

        @Override
        public void deleteMapExplorerManager(MapBean mapBean) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void addChangeListener(ChangeListener l) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void removeChangeListener(ChangeListener l) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

       
    }
}
