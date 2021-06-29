/*
 * To change this license header, choose License Headers in BpProject Properties.
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author bpodolski
 */
@Entity
@Table(name = "CG_PPROJECT_INFO")
public class CgProjectInfo implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_project")
    int id;

    @Column
    String name;

    @Column
    String description;

    @Column
    String path;

    @Column
    String dirPath;

    @Column//(name = "first", nullable = false)
    boolean relativePath;

    @Column(name = "XML_INFO", columnDefinition = "longvarchar")
    private String xmlInfo;

    @ElementCollection
    @CollectionTable(name = "CG_PROJECT_PROP")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CgProjectProperties> projectProperties;
//    protected Set<BpMapProperty> mapProperties = new LinkedHashSet<BpMapProperty>();
    

    public CgProjectInfo() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isRelativePath() {
        return relativePath;
    }

    public void setRelativePath(boolean relativePath) {
        this.relativePath = relativePath;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getXmlInfo() {
        return xmlInfo;
    }

    public void setXmlInfo(String xmlInfo) {
        this.xmlInfo = xmlInfo;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", path=" + path + ", defaultProject=" + relativePath + '}';
    }

    public List<CgProjectProperties> getProjectProperties() {
        return projectProperties;
    }

    public void setProjectProperties(List<CgProjectProperties> projectProperties) {
        this.projectProperties = projectProperties;
    }

    public void addLayerProperty(CgProjectProperties prop) {
        if (null == this.projectProperties) {
            projectProperties = new ArrayList<CgProjectProperties>();
        }
        projectProperties.add(prop);
    }

}
