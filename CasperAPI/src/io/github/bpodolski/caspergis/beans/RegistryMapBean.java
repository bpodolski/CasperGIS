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
public class RegistryMapBean {
    MapBean mapBean;

    public RegistryMapBean(MapBean mapBean) {
        this.mapBean = mapBean;
    }

    public MapBean getMapBean() {
        return mapBean;
    }
    
}
