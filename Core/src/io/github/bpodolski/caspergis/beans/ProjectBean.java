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
    
}
