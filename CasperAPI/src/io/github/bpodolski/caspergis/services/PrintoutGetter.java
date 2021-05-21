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
public abstract class PrintoutGetter {
     public abstract List<PrintoutBean> getPrintoutList(ProjectBean projectBean);

    public static PrintoutGetter getDefault() {
        PrintoutGetter printoutGetterService = Lookup.getDefault().lookup(PrintoutGetter.class);
        if (printoutGetterService == null) {
            printoutGetterService = new DefaultPrintoutGetter();        }
        return printoutGetterService;
    }

    private static class DefaultPrintoutGetter extends PrintoutGetter {

        @Override
        public List<PrintoutBean> getPrintoutList(ProjectBean projectBean) {
            ArrayList<PrintoutBean> printoutList = new <PrintoutBean>ArrayList();
            for (int i = 1; i < 2; i++) {
                printoutList.add(new PrintoutBean(null, i + ".TestPrintout - DefImpl"));
            }
            return printoutList;
        }
    }
}
