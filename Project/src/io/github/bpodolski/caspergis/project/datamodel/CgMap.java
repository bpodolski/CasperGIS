/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
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

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "POSITION")
    @OrderColumn
    int position;

    @Column
    boolean default_map;

    @OneToMany(mappedBy = "cgMap", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @org.hibernate.annotations.OrderBy(clause = "POSITION asc")
    protected List<CgLayer> cgLayers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "CG_MAP_PROP")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CgMapProperties> mapProperties = new ArrayList<>();

    public CgMap() {
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

    public boolean isDefault_map() {
        return default_map;
    }

    public void setDefault_map(boolean default_map) {
        this.default_map = default_map;
    }

    public List<CgMapProperties> getMapProperties() {
        return mapProperties;
    }

    public List<CgLayer> getLayers() {
        return cgLayers;
    }

    public void addLayer(CgLayer cgLayer) {
        if (cgLayer == null) {
            throw new IllegalArgumentException("Can't add a null Layer.");
        }
        this.getLayers().add(cgLayer);
    }

    public void setMapProperties(List<CgMapProperties> mapProperties) {
        this.mapProperties = mapProperties;
    }

    public void addLayerProperty(CgMapProperties prop) {
        if (null == this.mapProperties) {
            mapProperties = new ArrayList<>();
        }
        mapProperties.add(prop);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
