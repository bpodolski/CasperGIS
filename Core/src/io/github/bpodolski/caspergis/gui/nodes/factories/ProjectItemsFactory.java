/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.beans.ProjectElementBean;
import io.github.bpodolski.caspergis.gui.nodes.MapNode;
import io.github.bpodolski.caspergis.gui.nodes.PrintoutNode;
import io.github.bpodolski.caspergis.services.MapGetter;
import io.github.bpodolski.caspergis.services.PrintoutGetter;
import java.beans.IntrospectionException;
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
    private final MapGetter mapGetterService;
    private final PrintoutGetter printoutGetterService;

    private final List<ProjectElementBean> projectElementList = new ArrayList<>();

    public ProjectItemsFactory(ProjectBean projectBean) {
        this.projectBean = projectBean;
        this.mapGetterService = Lookup.getDefault().lookup(MapGetter.class);
        this.printoutGetterService = Lookup.getDefault().lookup(PrintoutGetter.class);

        projectElementList.addAll(mapGetterService.getMapList(projectBean));
//        projectElementList.addAll(printoutGetterService.getPrintoutList(projectBean));


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
                CasperInfo.io.getOut().println("ProjectItemsFactory; createNodeForKey = BeanType.MAP");
                node = new MapNode((MapBean) key);
            }
            if (key.getBeanType() == BeanType.PRINTOUT) {
                CasperInfo.io.getOut().println("ProjectItemsFactory; createNodeForKey = BeanType.PRINTOUT" );
                node = new PrintoutNode((PrintoutBean) key);
            }

        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
