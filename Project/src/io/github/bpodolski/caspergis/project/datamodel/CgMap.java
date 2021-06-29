/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author bpodolski
 */
@Entity
@Table(name = "CG_MAP")
public class CgMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MAP")
    int id;

    @Column(name = "ID_PROJECT")
    int project_id;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column
    boolean default_map;

    @ElementCollection
    @CollectionTable(name = "CG_MAP_PROP")
    @LazyCollection(LazyCollectionOption.FALSE)
//    protected Set<BpMapProperty> mapProperties = new LinkedHashSet<BpMapProperty>();
    private List<CgMapProperties> mapProperties;

    public CgMap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
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

    public boolean isDefault_map() {
        return default_map;
    }

    public void setDefault_map(boolean default_map) {
        this.default_map = default_map;
    }

    public List<CgMapProperties> getMapProperties() {
        return mapProperties;
    }

    public void setMapProperties(List<CgMapProperties> mapProperties) {
        this.mapProperties = mapProperties;
    }

    public void addLayerProperty(CgMapProperties prop) {
        if (null == this.mapProperties) {
            mapProperties = new ArrayList<CgMapProperties>();
        }
        mapProperties.add(prop);
    }

}
