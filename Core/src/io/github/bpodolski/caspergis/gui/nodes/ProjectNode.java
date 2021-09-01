/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.gui.nodes.factories.ProjectItemsFactory;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import java.beans.IntrospectionException;
import java.util.List;
import javax.swing.Action;
import org.openide.awt.Actions;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Utilities;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectNode extends BeanNode<ProjectBean> {

    InstanceContent ic = new InstanceContent();
    ModelMapsList model;
    ProjectItemsFactory factory;

    public ProjectNode(ProjectBean bean) throws IntrospectionException {
        this(bean, new ProjectItemsFactory(bean));
    }

    public ProjectNode(ProjectBean bean, ProjectItemsFactory factory) throws IntrospectionException {
        super(bean, Children.create(factory, true), Lookups.singleton(bean));
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/project.png");

        this.factory = factory;

        ic.add(new Index.Support() {
            @Override
            public Node[] getNodes() {
                return getChildren().getNodes(true);
            }

            @Override
            public int getNodesCount() {
                return getNodes().length;
            }

            @Override
            public void reorder(int[] perm) {
                factory.getModel().reorder(perm);
            }
        });

    }

    @Override
    public String getShortDescription() {
        return super.getBean().getDescription();
    }

    @Override
    public Action getPreferredAction() {
        return Actions.forID("Project", "io.github.bpodolski.caspergis.system.action.CTL_ProjectProperties");
    }

    @Override
    public Action[] getActions(boolean context) {
        List<? extends Action> actProject = Utilities.actionsForPath("Menu/Project");
        return actProject.toArray(new Action[actProject.size()]);
    }

}
