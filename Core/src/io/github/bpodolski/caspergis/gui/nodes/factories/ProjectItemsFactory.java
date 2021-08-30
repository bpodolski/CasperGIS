/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.ProjectitemBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.beans.ProjectitemBean;
import io.github.bpodolski.caspergis.gui.nodes.MapNode;
import io.github.bpodolski.caspergis.gui.nodes.PrintoutNode;
import io.github.bpodolski.caspergis.model.ModelMapElementsList;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import io.github.bpodolski.caspergis.services.MapExplorerManagerMgr;
import io.github.bpodolski.caspergis.services.MapListMgr;
import java.beans.IntrospectionException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectItemsFactory extends ChildFactory.Detachable<ProjectitemBean> {

    private final ProjectBean projectBean;
    MapListMgr mapListMgr;
    MapExplorerManagerMgr explorerManagerMgr;// = ExplorerManagerMgr.getDefault();

    private final List<ProjectitemBean> projectElementList = new ArrayList<>();
    
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
            projectElementList.addAll(mapListMgr.getMapList(projectBean));
        }

    }

    @Override
    protected boolean createKeys(List<ProjectitemBean> list) {
        list.addAll(this.projectElementList);
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
    
    public void add(ProjectitemBean bean) {
        projectElementList.add(bean);
        refresh(true);
    }
    
    public void add(int index, ProjectitemBean bean) {
        projectElementList.add(index, bean);
        refresh(true);
    }
    
    public void addAll(List list) {
        projectElementList.addAll(list);
        refresh(true);
    }
    
    public void addAll(int index, List list) {
        projectElementList.addAll(index, list);
        refresh(true);
    }
    
    public void removeChild(ProjectitemBean bean) {
        projectElementList.remove(bean);
        refresh(true);
    }
    
    @Override
    protected void addNotify() {
    }
    
    @Override
    protected void removeNotify() {
        
    }
    
    public void reorder(int[] perm) {
        ProjectitemBean[] reordered = new ProjectitemBean[this.projectElementList.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            ProjectitemBean c = (ProjectitemBean) this.projectElementList.get(i);
            reordered[j] = c;
        }
        this.projectElementList.clear();
        this.projectElementList.addAll(Arrays.asList(reordered));
        refresh(true);
        
    }
}
