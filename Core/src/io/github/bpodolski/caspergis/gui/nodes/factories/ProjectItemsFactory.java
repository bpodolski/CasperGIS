/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.CgRegistry;
import io.github.bpodolski.caspergis.Installer;
import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.beans.ProjectElementBean;
import io.github.bpodolski.caspergis.gui.nodes.MapNode;
import io.github.bpodolski.caspergis.gui.nodes.PrintoutNode;
import io.github.bpodolski.caspergis.services.ProjectInfoService;
import io.github.bpodolski.caspergis.services.PrintoutGetter;
import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectItemsFactory extends ChildFactory<ProjectElementBean> {

    private final ProjectBean projectBean;
    private final ProjectInfoService projectInfoService;
//    private final PrintoutGetter printoutGetterService;

    private final List<ProjectElementBean> projectElementList = new ArrayList<>();

    public ProjectItemsFactory(ProjectBean projectBean) {
        this.projectBean = projectBean;
        this.projectInfoService = Lookup.getDefault().lookup(ProjectInfoService.class);
//        this.printoutGetterService = Lookup.getDefault().lookup(PrintoutGetter.class);

        projectElementList.addAll(projectInfoService.getMapList(projectBean));

    }

    @Override
    protected boolean createKeys(List<ProjectElementBean> list) {
        list.addAll(this.projectElementList);
        return true;
    }

    @Override
    protected Node createNodeForKey(ProjectElementBean key) {
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
