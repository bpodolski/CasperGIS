/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author bpodolski
 */
//@Entity
//@Table(name = "CG_MAP_PROP")
@Embeddable
public class CgMapProperties implements Serializable {

    @org.hibernate.annotations.Parent
    protected CgMap cgMap;

    @Column
    private String key;

    @Column
    private String value;



    public CgMapProperties() {
    }

    public CgMapProperties(String key, String value) {
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

    public CgMap getCgMap() {
        return cgMap;
    }

    public void setCgMap(CgMap cgMap) {
        this.cgMap = cgMap;
    }

}
