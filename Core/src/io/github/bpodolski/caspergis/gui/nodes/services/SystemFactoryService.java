/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.services;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.services.ServiceProjectManager;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = ServiceProjectManager.class)
public class SystemFactoryService extends ServiceProjectManager  {

    @Override
    public ProjectBean getSystemProject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProjectBean> getProjectList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ProjectBean projectBean) {
//        CgRegistry.systemFactory.removeChild(projectBean);
    }

    @Override
    public void close(ProjectBean projectBean) {
//        CgRegistry.systemFactory.removeChild(projectBean);
    }

    @Override
    public void update(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(ProjectBean projectBean) {
//        CgRegistry.systemFactory.add(projectBean);
    }

    @Override
    public String getServiceName() {
        return "SystemFactoryService";
    }
    
}
