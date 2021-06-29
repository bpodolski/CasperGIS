/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author bpodolski
 */
@Entity
@Table(name = "CG_MAPLOB")
public class CgMapLob {

    @Id
    private int id;

    @OneToOne
    @MapsId
    private CgMap cgMap;

    @Column(name = "XML_TREE", columnDefinition = "longvarchar")
    private String xmlTree;

    public CgMapLob() {
    }

    public int getId() {
        return id;
    }

    public CgMap getCgMap() {
        return cgMap;
    }

    public void setCgMap(CgMap cgMap) {
        this.cgMap = cgMap;
    }

    public String getXmlTree() {
        return xmlTree;
    }

    public void setXmlTree(String xmlTree) {
        this.xmlTree = xmlTree;
    }
    
}
