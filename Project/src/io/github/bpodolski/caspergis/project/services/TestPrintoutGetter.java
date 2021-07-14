/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.PrintoutGetter;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = PrintoutGetter.class)
public class TestPrintoutGetter  extends PrintoutGetter {

        @Override
        public List<PrintoutBean> getPrintoutList(ProjectBean projectBean) {
            ArrayList<PrintoutBean> printoutList = new <PrintoutBean>ArrayList();
            for (int i = 1; i < 2; i++) {
                printoutList.add(new PrintoutBean(null, i + ".TestPrintout"));
            }
            return printoutList;
        }
    
}
