/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.PrintoutListMgr;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = PrintoutListMgr.class, path = "Project")
public class TestPrintoutGetter  extends PrintoutListMgr {

        @Override
        public List<PrintoutBean> getPrintoutList(ProjectBean projectBean) {
            ArrayList<PrintoutBean> printoutList = new <PrintoutBean>ArrayList();
            for (int i = 1; i < 2; i++) {
                printoutList.add(new PrintoutBean(null, i + ".TestPrintout"));
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
