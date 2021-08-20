/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.CgRegistry;
import io.github.bpodolski.caspergis.Installer;
import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.beans.ProjectitemBean;
import io.github.bpodolski.caspergis.gui.nodes.MapNode;
import io.github.bpodolski.caspergis.gui.nodes.PrintoutNode;
import io.github.bpodolski.caspergis.services.MapListMgr;
import io.github.bpodolski.caspergis.services.ProjectListMgr;
import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectItemsFactory extends ChildFactory<ProjectitemBean> {

    private final ProjectBean projectBean;
    MapListMgr mapListMgr;

    private final List<ProjectitemBean> projectElementList = new ArrayList<>();

    public ProjectItemsFactory(ProjectBean projectBean) {
        this.projectBean = projectBean;
        File f = new File(projectBean.getPath());

        Collection<? extends MapListMgr> srvList = Lookups.forPath("Project").lookupAll(MapListMgr.class);
        if (srvList.iterator().hasNext()) {
            this.mapListMgr = srvList.iterator().next();
        }else this.mapListMgr = MapListMgr.getDefault();

//        this.mapListMgr = Lookup.getDefault().lookup(MapListMgr.class);
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
                if (mb.isActive()) {
                    CgRegistry cgr = Installer.cgRegistry;
                    cgr.setActiveMapBean(mb);
                    cgr.setActiveMapNode((MapNode) node);
                }

            }
            if (key.getBeanType() == BeanType.PRINTOUT) {

                node = new PrintoutNode((PrintoutBean) key);
            }

        } catch (IntrospectionException | PropertyVetoException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
