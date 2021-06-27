/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@Entity
@Table(name = "CG_PROJECT")
public class CgProject {

    @Id
    @GeneratedValue
    @Column(name = "ID_PROJECT")
    int id;

    @Column
    String name;

    @Column
    String description;

    @Column
    String path;

    @Column(name = "XML_INFO", columnDefinition = "longvarchar")
    private String xmlInfo;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getXmlInfo() {
        return xmlInfo;
    }

    public void setXmlInfo(String xmlInfo) {
        this.xmlInfo = xmlInfo;
    }

    @Override
    public String toString() {
        return "CgProject{" + "id=" + id + ", name=" + name + ", description=" + description + ", path=" + path + '}';
    }
}
