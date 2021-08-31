/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.services;

import io.github.bpodolski.caspergis.CgRegistry;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import io.github.bpodolski.caspergis.services.MapListMgr;
import io.github.bpodolski.caspergis.services.ProjectListMgr;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = MapListMgr.class, path = "Core")
public class CoreMapListMgr extends MapListMgr {

    @Override
    public List<MapBean> getMapList(ProjectBean projectBean) {
        ModelMapsList model = CgRegistry.modelMapsList.get(projectBean);
        return (List<MapBean>) model.list();
    }

    @Override
    public void add(MapBean mapBean, ProjectBean projectBean) {
        ModelMapsList model = CgRegistry.modelMapsList.get(projectBean);
        model.add(mapBean);
    }

    @Override
    public void delete(MapBean mapBean) {
//        ModelMapsList model = CgRegistry.modelMapsList.get(projectBean);
//        model.remove(mapBean);
    }

    @Override
    public void close(MapBean mapBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MapBean mapBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
