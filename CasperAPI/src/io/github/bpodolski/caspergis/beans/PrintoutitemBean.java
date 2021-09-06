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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class PrintoutitemBean implements Transferable {

    private List<PrintoutitemBean> printoutElementBeans;
    public static final DataFlavor PRINTOUTELEMENT_FLAVOR = new DataFlavor(MapitemBean.class, "MapElementBean");

    private BeanType beanType = BeanType.PRINTOUT_ELEMENT;
    private String name = "Printout Element";
    private String displayName = "Printout Element";
    //position in project list, used by comparator, (calculate by position in parent node children list)
    int position = 0;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

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
        return new DataFlavor[]{PrintoutitemBean.PRINTOUTELEMENT_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == PrintoutitemBean.PRINTOUTELEMENT_FLAVOR;
    }

    @Override
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (flavor == PrintoutitemBean.PRINTOUTELEMENT_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    public List<PrintoutitemBean> getPrintoutElementBeans() {
        if (printoutElementBeans == null) {
            printoutElementBeans = new ArrayList<PrintoutitemBean>();
        }
        return printoutElementBeans;
    }

    public void setPrintoutElementBeans(List<PrintoutitemBean> printoutElementBeans) {
        this.printoutElementBeans = printoutElementBeans;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
