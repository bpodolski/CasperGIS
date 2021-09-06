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
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@Entity
@Table(name = "CGSYS_PROJECT")
public class CgSysProject {

    @Id
    @GeneratedValue
    @Column(name = "ID_PROJECT")
    int id;

    @Column
    String path;

    @Column
    @OrderColumn
    int position;

    @Column
    boolean hidden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
