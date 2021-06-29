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
//@Table(name = "CG_PRINTOUT_PROP")
@Embeddable
public class CgPrintoutProperties implements Serializable {

    @org.hibernate.annotations.Parent
    protected CgPrintout cgPrintout;

    @Column
    private String key;

    @Column
    private String value;

    public CgPrintoutProperties() {
    }

    public CgPrintoutProperties(String key, String value) {
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

    public CgPrintout getCgPrintout() {
        return cgPrintout;
    }

    public void setCgPrintout(CgPrintout cgPrintout) {
        this.cgPrintout = cgPrintout;
    }

    
}
