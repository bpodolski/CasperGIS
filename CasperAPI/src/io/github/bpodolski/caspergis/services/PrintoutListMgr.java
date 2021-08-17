/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.services;

import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 * Abstract class - The base for the service that provides the list of printouts in project
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public abstract class PrintoutListMgr {
     public abstract List<PrintoutBean> getPrintoutList(ProjectBean projectBean);
     
     /**
     * Add printout     *
     * @param printoutBean  - printout to add
     * @param projectBean - project with printouts
     */
    public abstract void add(PrintoutBean printoutBean, ProjectBean projectBean);

    /**
     * Delete printout     *
     * @param printoutBean
     */
    public abstract void delete(PrintoutBean printoutBean);

    /**
     * Close printout
     *
     * @param printoutBean
     */
    public abstract void close(PrintoutBean printoutBean);

    /**
     * Update printout
     *
     * @param printoutBean
     */
    public abstract void update(PrintoutBean printoutBean);

    public static PrintoutListMgr getDefault() {
        PrintoutListMgr service = Lookup.getDefault().lookup(PrintoutListMgr.class);
        if (service == null) {
            service = new DefaultPrintoutListMgr();        }
        return service;
    }

    private static class DefaultPrintoutListMgr extends PrintoutListMgr {

        @Override
        public List<PrintoutBean> getPrintoutList(ProjectBean projectBean) {
            ArrayList<PrintoutBean> printoutList = new <PrintoutBean>ArrayList();
            for (int i = 1; i < 2; i++) {
                printoutList.add(new PrintoutBean(null, i + ".TestPrintout - DefImpl"));
            }
            return printoutList;
        }

        @Override
        public void add(PrintoutBean printoutBean, ProjectBean projectBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void delete(PrintoutBean printoutBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void close(PrintoutBean printoutBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void update(PrintoutBean printoutBean) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
