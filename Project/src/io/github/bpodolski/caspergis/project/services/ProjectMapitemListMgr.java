/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.services;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.model.ModelMapitemsList;
import io.github.bpodolski.caspergis.project.CgRegistryProject;
import io.github.bpodolski.caspergis.project.dao.ProjectDAO;
import io.github.bpodolski.caspergis.project.datamodel.CgLayer;
import io.github.bpodolski.caspergis.project.datamodel.CgMap;
import io.github.bpodolski.caspergis.services.MapitemListMgr;
import java.util.Iterator;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * // * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ServiceProvider(service = MapitemListMgr.class, path = "Project")
public class ProjectMapitemListMgr extends MapitemListMgr {

    private boolean modelReady = false;

    @Override
    public void initModel(MapBean mapBean) {
        ProjectDAO dao = (ProjectDAO) CgRegistryProject.cgMapDaoMap.get(mapBean);
        modelReady = false;
        if (dao != null) {
            ModelMapitemsList modelMapitemsList = new ModelMapitemsList();
            CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);

            Iterator<CgLayer> itr = dao.getLayers(cgMap).iterator();

            while (itr.hasNext()) {
                CgLayer cgl = itr.next();
                LayerBean lb = new LayerBean(null, cgl.getName());

                CgRegistryProject.cgLayerMap.put(lb, cgl);
                CgRegistryProject.cgLayerDaoMap.put(lb, dao);

                modelMapitemsList.add(lb);
            }
            CgRegistryProject.cgLayerModelMap.put(mapBean, modelMapitemsList);
            modelReady = true;
        }

    }

    @Override
    public List<MapitemBean> getMapItemsList(MapBean mapBean) {
        ModelMapitemsList modelMapitemsList = (ModelMapitemsList) CgRegistryProject.cgLayerModelMap.get(mapBean);
        return modelMapitemsList.list();
    }

    @Override
    public void add(LayerBean layerBean, MapBean mapBean) {
        ProjectDAO dao = (ProjectDAO) CgRegistryProject.cgMapDaoMap.get(mapBean);
        CgMap cgMap = (CgMap) CgRegistryProject.cgMapMap.get(mapBean);

        CgLayer cgl = new CgLayer();
        cgl.setName(layerBean.getName());
        cgl.setCgMap(cgMap);

        dao.addLayer(cgl);

        CgRegistryProject.cgLayerMap.put(layerBean, cgl);
        CgRegistryProject.cgLayerDaoMap.put(layerBean, dao);
    }

    @Override
    public void update(LayerBean layerBean) {
        ProjectDAO dao = (ProjectDAO) CgRegistryProject.cgLayerDaoMap.get(layerBean);
        CgLayer cgl = (CgLayer) CgRegistryProject.cgLayerMap.get(layerBean);

    }

    @Override
    public void close(LayerBean layerBean) {
        ModelMapitemsList modelMapitemsList = findModel(layerBean);
        modelMapitemsList.remove(layerBean);

        ProjectDAO dao = (ProjectDAO) CgRegistryProject.cgLayerDaoMap.get(layerBean);
        CgLayer cgl = (CgLayer) CgRegistryProject.cgLayerMap.get(layerBean);
        dao.deleteLayer(cgl);

        CgRegistryProject.cgLayerMap.remove(cgl);
        CgRegistryProject.cgLayerDaoMap.remove(cgl);
    }

    @Override
    public ModelMapitemsList getModel(MapBean mapBean) {
        ModelMapitemsList modelMapitemsList = (ModelMapitemsList) CgRegistryProject.cgLayerModelMap.get(mapBean);
        return modelMapitemsList;
    }

    private ModelMapitemsList findModel(LayerBean layerBean) {
        MapitemBean mbi = layerBean;
        for (Object m : CgRegistryProject.cgLayerModelMap.keySet()) {
            MapBean mb = (MapBean) m;
            ModelMapitemsList modelMapitemsList = (ModelMapitemsList) CgRegistryProject.cgLayerModelMap.get(mb);
            if (modelMapitemsList.list().indexOf(mbi) > -1) {
                return modelMapitemsList;
            }

        }

        return null;
    }

    @Override
    public boolean isModelReady() {
       return modelReady;
    }

}
