/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.netbeans.api.settings.ConvertAsJavaBean;

/**
 *
 * @author bpodolski
 */


@ConvertAsJavaBean()
public class ProjectBean {

    //name of project
    String name = "CasperGIS Progect";
    //path to file of project
    String path;
    //tooltip for node, for SystemTmpProject - "System temporary project", for other blank or path 
    String description;
    //false - project visible on project list
    boolean hidden = false;
    //position in project list, used by comparator, (calculate by position in parent node children list)
    int position = 0;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ProjectBean() {

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, name);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        String oldValue = this.path;
        this.path = path;
        propertyChangeSupport.firePropertyChange("path", oldValue, path);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldValue, description);
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        boolean oldValue = this.hidden;
        this.hidden = hidden;
        propertyChangeSupport.firePropertyChange("hidden", oldValue, hidden);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        int oldValue = this.position;
        this.position = position;
        propertyChangeSupport.firePropertyChange("position", oldValue, position);
    }

}
