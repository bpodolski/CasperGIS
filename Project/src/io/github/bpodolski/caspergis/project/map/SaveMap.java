/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.map;

import java.awt.event.ActionListener;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.services.MapitemListMgr;
import io.github.bpodolski.caspergis.utils.LayerFileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
@ActionID(
        category = "Map",
        id = "io.github.bpodolski.caspergis.project.map.SaveMap"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/map/map_activate.png",
        displayName = "#CTL_SaveMap"
)
@ActionReferences({
    @ActionReference(path = "Menu/Map", position = 150),
    @ActionReference(path = "Toolbars/Map", position = 150)
})
@NbBundle.Messages("CTL_SaveMap=Add Layer")
public class SaveMap implements ActionListener {

    private MapBean mapBean;
    MapitemListMgr mapitemListMgrProject = Lookups.forPath("Project").lookupAll(MapitemListMgr.class).iterator().next();

    public SaveMap(MapBean context) {
        this.mapBean = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mapBean.isActive()) {
            if (mapitemListMgrProject != null) {
                var model = mapitemListMgrProject.getModel(mapBean);
                int pos = 0;

                Iterator<MapitemBean> itr = model.list().iterator();
                while (itr.hasNext()) {
                    pos++;
                    MapitemBean mb = itr.next();
                    mb.setPosition(pos);
                    LayerBean layerBean = (LayerBean) mb;                    
                    mapitemListMgrProject.update(layerBean);
                }

            }
        }
    }

}
