/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bpodolski
 */
//@Entity
//@Table(name = "CG_LAYER_PROP")
@Embeddable
public class CgLayerProperties implements Serializable {

    @org.hibernate.annotations.Parent
    protected CgLayer cgLayer;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    public CgLayerProperties() {
    }

    public CgLayerProperties(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CgLayer getCgLayer() {
        return cgLayer;
    }

    public void setCgLayer(CgLayer cgLayer) {
        this.cgLayer = cgLayer;
    }

}
