/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author bpodolski
 */
//@Entity
//@Table(name = "CG_PROJECT_PROP")
@Embeddable
public class CgProjectProperties implements Serializable {

    @Id
    @org.hibernate.annotations.Parent
    protected CgProjectInfo cgProjectInfo;

    @Column
    private String key;

    @Column
    private String value;

    public CgProjectProperties() {
    }

    public CgProjectProperties(String key, String value) {
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

    public CgProjectInfo getCgProjectInfo() {
        return cgProjectInfo;
    }

    public void setCgProjectInfo(CgProjectInfo cgProjectInfo) {
        this.cgProjectInfo = cgProjectInfo;
    }

}
