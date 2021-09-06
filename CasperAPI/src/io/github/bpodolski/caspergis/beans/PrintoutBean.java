/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class PrintoutBean extends ProjectitemBean {

    private ArrayList<ProjectitemBean> projectElementBeans;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PrintoutBean(ArrayList<ProjectitemBean> projectElementBeans) {
        this(projectElementBeans, "Printouts");
        this.setBeanType(BeanType.PRINTOUT);
    }

    public PrintoutBean(ArrayList<ProjectitemBean> projectElementBeans, String name) {
        this.setName(name);
        this.setDisplayName(name);
        this.projectElementBeans = projectElementBeans;
        this.setBeanType(BeanType.PRINTOUT);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
