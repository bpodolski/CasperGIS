/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import io.github.bpodolski.caspergis.gui.nodes.ProjectNode;
import io.github.bpodolski.caspergis.services.ProjectInfoService;
import io.github.bpodolski.caspergis.services.ServiceProjectManager;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class SystemFactory extends ChildFactory.Detachable<ProjectBean> implements ChangeListener {

    private ModelProjectList model = new ModelProjectList(new ArrayList());

    public SystemFactory(ModelProjectList model) {
        this.model = model;
    }

    @Override
    protected boolean createKeys(List<ProjectBean> toPopulate) {
        toPopulate.addAll(model.list());
        return true;
    }

    @Override
    protected Node createNodeForKey(ProjectBean key) {
        ProjectNode node = null;
        try {
            node = new ProjectNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

    public void add(ProjectBean bean) {
//        projectList.add(bean);
        this.model.add(bean);
        refresh(true);
    }

    public void removeChild(ProjectBean bean) {
//        projectList.remove(bean);
        this.model.remove(bean);
        refresh(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        refresh(true);
    }

    @Override
    protected void addNotify() {
        model.addChangeListener(this);
    }

    @Override
    protected void removeNotify() {
        model.removeChangeListener(this);
    }
}
