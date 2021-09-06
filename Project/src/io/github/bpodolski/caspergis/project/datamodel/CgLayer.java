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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author bpodolski
 */
@Entity
@Table(name = "CG_LAYER")
public class CgLayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_LAYER")
    int id;

    @ManyToOne(fetch = FetchType.LAZY) // Defaults to EAGER
    @JoinColumn(name = "ID_MAP", nullable = false)
    CgMap cgMap;

    @Column(name = "ID_GROUP")
    int group_id;

    @Column
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "PATH")
    String path;

    @Column(name = "READABLE")
    boolean readable;

    @Column(name = "VISIBLE")
    boolean visible;

    @Column(name = "TRANSPARENT")
    int transparent;

    @Column(name = "POSITION")
    @OrderColumn
    int position;

    @ElementCollection
    @CollectionTable(name = "CG_LAYER_PROP")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CgLayerProperties> layerProperties = new ArrayList<>();

    @OneToOne(mappedBy = "cgLayer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    protected CgLayerStyle cgLayerStyle;

    public CgLayer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CgMap getCgMap() {
        return cgMap;
    }

    public void setCgMap(CgMap cgMap) {
        this.cgMap = cgMap;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
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
        if (transparent > 100) {
            transparent = 100;
        }
        if (transparent < 0) {
            transparent = 0;
        }
        this.transparent = transparent;
    }

    public List<CgLayerProperties> getLayerProperties() {
        return layerProperties;
    }

    public void setLayerProperties(List<CgLayerProperties> layerProperties) {
        this.layerProperties = layerProperties;
    }

    public void addLayerProperty(CgLayerProperties prop) {
        if (null == this.layerProperties) {
            layerProperties = new ArrayList<CgLayerProperties>();
        }
        layerProperties.add(prop);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CgLayerStyle getCgLayerStyle() {
        return cgLayerStyle;
    }

    public void setCgLayerStyle(CgLayerStyle cgLayerStyle) {
        this.cgLayerStyle = cgLayerStyle;
    }

}
