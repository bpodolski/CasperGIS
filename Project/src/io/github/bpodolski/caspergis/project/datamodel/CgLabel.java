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
@Table(name = "CG_LABEL")
public class CgLabel implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_LABEL")
    int id;
    
    @Column(name = "ID_LAYER")
    int layer_id;
    
    @Column(name = "EXPRESSION")
    String expression;
    
    @Column(name = "VISIBLE")
    boolean visible;

    public CgLabel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLayer_id() {
        return layer_id;
    }

    public void setLayer_id(int layer_id) {
        this.layer_id = layer_id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Label{" + "id=" + id + ", layer_id=" + layer_id + ", expression=" + expression + ", visible=" + visible + '}';
    }
    

}
