/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapBeanEnv {
    MapBean mapBean;
    boolean active;

    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public MapBeanEnv(MapBean mapBean, boolean active) {
        this.mapBean = mapBean;
        this.active = active;
    }
    
    

    public MapBeanEnv(MapBean mapBean) {
        this(mapBean, false);
    }

    public MapBean getMapBean() {
        return mapBean;
    }
    
}
