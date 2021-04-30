/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

import static io.github.bpodolski.caspergis.beans.MapElementBean.MAPELEMENT_FLAVOR;
import io.github.bpodolski.caspergis.utils.BeanType;
import io.github.bpodolski.caspergis.utils.LayerFileFilter;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectElementBean implements Transferable {

    private List<ProjectElementBean> projectElementBeans;
    public static final DataFlavor PROJECTELEMENT_FLAVOR = new DataFlavor(ProjectElementBean.class, "ProjectElementBean");

    private BeanType beanType = BeanType.PROJECT_ELEMENT;
    private String name = "Project Element";
    private String displayName = "Project Element";

    public List<ProjectElementBean> getProjectElementBeans() {
        return projectElementBeans;
    }

    public void setProjectElementBeans(List<ProjectElementBean> projectElementBeans) {
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

}
