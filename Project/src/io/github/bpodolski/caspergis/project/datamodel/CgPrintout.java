/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author bpodolski
 */
@Entity
@Table(name = "CG_PRINTOUT")
public class CgPrintout {

    @Id
    @GeneratedValue
    @Column(name = "ID_PRINTOUT")
    private int id;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "POSITION")
    @OrderColumn
    int position;

    @Column(name = "XML_TREE", columnDefinition = "longvarchar")
    private String xmlTree;

    @ElementCollection
    @CollectionTable(name = "CG_PRINTOUT_PROP")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CgPrintoutProperties> printoutProperties;

//    protected Set<BpMapProperty> mapProperties = new LinkedHashSet<BpMapProperty>();
    public CgPrintout() {
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public String getXmlTree() {
        return xmlTree;
    }

    public void setXmlTree(String xmlTree) {
        this.xmlTree = xmlTree;
    }

    public List<CgPrintoutProperties> getPrintoutProperties() {
        return printoutProperties;
    }

    public void setPrintoutProperties(List<CgPrintoutProperties> printoutProperties) {
        this.printoutProperties = printoutProperties;
    }

    public void addLayerProperty(CgPrintoutProperties prop) {
        if (null == this.printoutProperties) {
            printoutProperties = new ArrayList<CgPrintoutProperties>();
        }
        printoutProperties.add(prop);
    }
    
    
}
