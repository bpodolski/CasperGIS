/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;;

import java.util.ArrayList;

/**
 *
 * @author bpodolski
 */
public class ProjectBean {

    
    String name;
    String path;
    ArrayList<MapBean> listMapBean;
    ArrayList<PrintoutBean> listPrintOutBean;
    boolean active = false;
    boolean hidden = false;
    int position = 0;

    public ProjectBean(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<MapBean> getListMapBean() {
        return listMapBean;
    }

    public void setListMapBean(ArrayList<MapBean> listMapBean) {
        this.listMapBean = listMapBean;
    }

    public ArrayList<PrintoutBean> getListPrintOutBean() {
        return listPrintOutBean;
    }

    public void setListPrintOutBean(ArrayList<PrintoutBean> listPrintOutBean) {
        this.listPrintOutBean = listPrintOutBean;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
