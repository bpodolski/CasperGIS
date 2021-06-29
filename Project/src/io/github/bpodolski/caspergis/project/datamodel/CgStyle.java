/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author bpodolski
 */
@Entity
@Table(name = "CG_STYLE")
public class CgStyle  implements Serializable {
    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID_STYLE")
    int id;
    
    @Column(name = "NAME")
    String name;
    
    @Column(name = "DESCRIPTION")
    String description;
    
    @Lob
    String SLD;

    public CgStyle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSLD() {
        return SLD;
    }

    public void setSLD(String SLD) {
        this.SLD = SLD;
    }
    
}
