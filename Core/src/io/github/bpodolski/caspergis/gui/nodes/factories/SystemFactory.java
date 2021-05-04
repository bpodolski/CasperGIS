/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.gui.nodes.ProjectNode;
import io.github.bpodolski.caspergis.services.ProjectGetter;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class SystemFactory extends ChildFactory<ProjectBean> {

    @Override
    protected boolean createKeys(List<ProjectBean> toPopulate) {
        List<ProjectBean> listP = new ArrayList<>();
        ProjectBean systemProjectBean = null;

        ProjectGetter projectService = Lookup.getDefault().lookup(ProjectGetter.class);

        systemProjectBean = projectService.getSystemProject();
        listP.add(systemProjectBean);

        listP.addAll(projectService.getProjectList());

        toPopulate.addAll(listP);
        return true;
    }

    @Override
    protected Node createNodeForKey(ProjectBean key) {
        ProjectNode node = null;
        try {
            node = new ProjectNode(key, new ProjectItemsFactory(key));
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

}
