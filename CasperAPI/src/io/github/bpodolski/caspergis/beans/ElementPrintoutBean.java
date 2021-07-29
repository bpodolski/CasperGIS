/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ElementPrintoutBean  implements Transferable {

    private List<ElementPrintoutBean> printoutElementBeans;
    public static final DataFlavor PRINTOUTELEMENT_FLAVOR = new DataFlavor(ElementMapBean.class, "MapElementBean");

    private BeanType beanType = BeanType.PRINTOUT_ELEMENT;
    private String name = "Printout Element";
    private String displayName = "Printout Element";
    
    
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
        return new DataFlavor[]{ElementPrintoutBean.PRINTOUTELEMENT_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == ElementPrintoutBean.PRINTOUTELEMENT_FLAVOR;
    }

    @Override
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (flavor == ElementPrintoutBean.PRINTOUTELEMENT_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    public List<ElementPrintoutBean> getPrintoutElementBeans() {
        if (printoutElementBeans == null) {
            printoutElementBeans = new ArrayList<ElementPrintoutBean>();
        }
        return printoutElementBeans;
    }

    public void setPrintoutElementBeans(List<ElementPrintoutBean> printoutElementBeans) {
        this.printoutElementBeans = printoutElementBeans;
    }

}
