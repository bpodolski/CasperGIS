/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.map;

import io.github.bpodolski.caspergis.beans.LayerBean;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.services.MapitemListMgr;
import io.github.bpodolski.caspergis.utils.LayerFileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
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
        id = "io.github.bpodolski.caspergis.project.map.AddLayer"
)
@ActionRegistration(
        iconBase = "io/github/bpodolski/caspergis/project/map/map_activate.png",
        displayName = "#CTL_AddLayer"
)
@ActionReferences({
    @ActionReference(path = "Menu/Map", position = 100),
    @ActionReference(path = "Toolbars/Map", position = 100)
})
@NbBundle.Messages("CTL_AddLayer=Add Layer")
public class AddLayer implements ActionListener {

    private MapBean mapBean;
    MapitemListMgr mapitemListMgrProject = Lookups.forPath("Project").lookupAll(MapitemListMgr.class).iterator().next();

    public AddLayer(MapBean context) {
        this.mapBean = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mapBean.isActive()) {
            if (mapitemListMgrProject != null) {
                var chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setFileFilter(new FileNameExtensionFilter("SHP files", "shp", "shp"));
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setMultiSelectionEnabled(true);
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    var files = chooser.getSelectedFiles();
                    for (int i = 0; i < files.length; i++) {
                        var f = files[i];
                        if (LayerFileFilter.LAYER_FILEFILTER.accept(f)) {
                            LayerBean lb = new LayerBean(f.getName());
                            lb.setConnectionStr(f.getAbsolutePath());

                            this.mapitemListMgrProject.add(lb, mapBean);

                        }
                    }
                } else {
                    NotifyDescriptor d = new NotifyDescriptor.Confirmation(
                            "Message",
                            "Title");
                    DialogDisplayer.getDefault().notify(d);
                }

            }
        }
    }
}
