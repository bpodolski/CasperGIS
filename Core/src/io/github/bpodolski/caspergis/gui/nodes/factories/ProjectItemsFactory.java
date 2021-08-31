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
import java.util.Collection;
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

        Collection<? extends MapListMgr> srvList = Lookups.forPath("Project").lookupAll(MapListMgr.class);
        if (srvList.iterator().hasNext()) {
            this.mapListMgr = srvList.iterator().next();
        } else {
            this.mapListMgr = MapListMgr.getDefault();
        }

        Collection<? extends MapExplorerManagerMgr> srvMapExp = Lookups.forPath("Core").lookupAll(MapExplorerManagerMgr.class);
        if (srvMapExp.iterator().hasNext()) {
            this.explorerManagerMgr = srvMapExp.iterator().next();
        } else {
            this.explorerManagerMgr = MapExplorerManagerMgr.getDefault();
        }

        if (f.exists()) {
            this.modelMapsList.addAll(mapListMgr.getMapList(projectBean));
        }
        
        CgRegistry.modelMapsList.put(projectBean, modelMapsList);

    }

    @Override
    protected boolean createKeys(List<ProjectitemBean> list) {
       
        list.addAll(this.modelMapsList.list());
        return true;
    }

    @Override
    protected Node createNodeForKey(ProjectitemBean key) {
        BeanNode node = null;

        try {
            if (key.getBeanType() == BeanType.MAP) {
                MapBean mb = (MapBean) key;
                node = new MapNode(mb);

                explorerManagerMgr.addMapExplorerManager(mb);

            }
            if (key.getBeanType() == BeanType.PRINTOUT) {

                node = new PrintoutNode((PrintoutBean) key);
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
}
