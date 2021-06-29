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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bpodolski
 */
@Entity
@Table(name = "CG_GROUP")
public class CgGroup  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_GROUP")
    int id;
        
    @Column(name = "ID_MAP")
    int map_id;
    
    @Column(name = "NAME")
    String name;
    
    @Column(name = "DESCRIPTION")
    String description;
    
    @Column(name = "VISIBLE")
    boolean visible;
    
    @Column(name = "TRANSPARENT")
    int transparent;

    public CgGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMap_id() {
        return map_id;
    }

    public void setMap_id(int map_id) {
        this.map_id = map_id;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getTransparent() {
        return transparent;
    }

    public void setTransparent(int transparent) {
        if(transparent > 100) transparent = 100;
        if(transparent < 0)   transparent = 0;
        this.transparent = transparent;
    }

    @Override
    public String toString() {
        return "Group{" + "id=" + id + ", map_id=" + map_id + ", name=" + name + ", description=" + description + ", visible=" + visible + ", transparent=" + transparent + '}';
    }
    
}
