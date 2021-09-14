/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.CgRegistry;
import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.beans.ProjectitemBean;
import io.github.bpodolski.caspergis.gui.nodes.MapNode;
import io.github.bpodolski.caspergis.gui.nodes.PrintoutNode;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import io.github.bpodolski.caspergis.services.MapExplorerManagerMgr;
import io.github.bpodolski.caspergis.services.MapListMgr;
import java.beans.IntrospectionException;
import java.io.File;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectItemsFactory extends ChildFactory.Detachable<ProjectitemBean> implements ChangeListener {

    private final ProjectBean projectBean;
    MapListMgr mapListMgr;
    MapExplorerManagerMgr explorerManagerMgr;

    ModelMapsList modelMapsList = new ModelMapsList();

    public ProjectItemsFactory(ProjectBean projectBean) {
        this.projectBean = projectBean;
        File f = new File(projectBean.getPath());

        this.mapListMgr = Lookups.forPath("Project").lookupAll(MapListMgr.class).iterator().next();
        if (this.mapListMgr == null) {
            this.mapListMgr = MapListMgr.getDefault();
        }

        this.explorerManagerMgr =  Lookups.forPath("Core").lookupAll(MapExplorerManagerMgr.class).iterator().next();
        if (this.explorerManagerMgr == null) {
               this.explorerManagerMgr = MapExplorerManagerMgr.getDefault();
        }

        if (f.exists()) {
            this.modelMapsList.addAll(mapListMgr.getMapList(projectBean));
        }
//        
//        
//
//        CgRegistry.modelMapsList.put(projectBean, modelMapsList);

    }

    @Override
    protected boolean createKeys(List<ProjectitemBean> listProjectitemBean) {

        listProjectitemBean.addAll(this.modelMapsList.list());
        return true;
    }

    @Override
    protected Node createNodeForKey(ProjectitemBean projectitemBean) {
        BeanNode node = null;

        try {
            if (projectitemBean.getBeanType() == BeanType.MAP) {
                MapBean mb = (MapBean) projectitemBean;
                node = new MapNode(mb);
                MapNode mn = (MapNode) node;
                mn.setFactory(this);

                explorerManagerMgr.addMapExplorerManager(mb);
//                explorerManagerMgr.addModelMapitems(mb);

            }
            if (projectitemBean.getBeanType() == BeanType.PRINTOUT) {

                node = new PrintoutNode((PrintoutBean) projectitemBean);
            }

        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

    @Override
    protected void addNotify() {
        modelMapsList.addChangeListener(this);
    }

    @Override
    protected void removeNotify() {
        modelMapsList.removeChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        refresh(true);
    }

    public ModelMapsList getModel() {
        return modelMapsList;
    }

}
