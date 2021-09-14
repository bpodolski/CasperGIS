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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.DeleteAction;
import org.openide.actions.MoveDownAction;
import org.openide.actions.MoveUpAction;
import org.openide.actions.RenameAction;
import org.openide.actions.ReorderAction;
import org.openide.awt.Actions;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectNode extends BeanNode<ProjectBean> {

    InstanceContent ic = new InstanceContent();
    ModelMapsList model;
    ProjectItemsFactory factory;
//
//    public ProjectNode(ProjectBean bean) throws IntrospectionException {
//        this(bean, new ProjectItemsFactory(bean), new InstanceContent());
//    }

    //do testow:
    public ProjectNode(ProjectBean bean) throws IntrospectionException {
        super(bean, Children.LEAF, Lookups.singleton(bean));
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/project.png");
    }

    public ProjectNode(ProjectBean bean, ProjectItemsFactory factory, InstanceContent ic) throws IntrospectionException {
        super(bean, Children.create(factory, true), new ProxyLookup(new AbstractLookup(ic), Lookups.singleton(bean)));
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
        
        ic.add(this);

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
        ArrayList actList = new ArrayList();
        List<? extends Action> actProject = Utilities.actionsForPath("Menu/Project");

//        ReorderAction reorderAction = SystemAction.get(ReorderAction.class);
        MoveUpAction moveUpAction = SystemAction.get(MoveUpAction.class);
        MoveDownAction moveDownAction = SystemAction.get(MoveDownAction.class);
        RenameAction renameAction = SystemAction.get(RenameAction.class);

        actList.add(moveUpAction);
        actList.add(moveDownAction);
        actList.add(renameAction);

        actList.addAll(actProject);

        return (Action[]) actList.toArray(new Action[actList.size()]);
    }

    @Override
    public String getName() {
        ProjectBean bean = getLookup().lookup(ProjectBean.class);
        if (null != bean.getName()) {
            return bean.getName();
        }
        return super.getDisplayName();
    }

    @Override
    public void setName(String newDisplayName) {
        ProjectBean bean = getLookup().lookup(ProjectBean.class);
        String oldDisplayName = bean.getName();
        bean.setName(newDisplayName);
        fireNameChange(oldDisplayName, newDisplayName);
    }

    @Override
    public boolean canRename() {
        return true;
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        fireNodeDestroyed();
    }

}
