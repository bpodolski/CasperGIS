/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectitemBean implements Transferable {

    private List<ProjectitemBean> projectElementBeans;
    public static final DataFlavor PROJECTELEMENT_FLAVOR = new DataFlavor(ProjectitemBean.class, "ProjectElementBean");

    private BeanType beanType = BeanType.PROJECT_ELEMENT;
    private String name = "Project Element";
    private String displayName = "Project Element";

    //position in project list, used by comparator, (calculate by position in parent node children list)
    int position = 0;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public List<ProjectitemBean> getProjectElementBeans() {
        return projectElementBeans;
    }

    public void setProjectElementBeans(List<ProjectitemBean> projectElementBeans) {
        this.projectElementBeans = projectElementBeans;
    }

    public BeanType getBeanType() {
        return beanType;
    }

    public void setBeanType(BeanType beanType) {
        this.beanType = beanType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == PROJECTELEMENT_FLAVOR;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor == PROJECTELEMENT_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
