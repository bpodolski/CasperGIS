/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import io.github.bpodolski.caspergis.gui.nodes.ProjectNode;
import io.github.bpodolski.caspergis.services.ProjectObjectMgr;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class SystemFactory extends ChildFactory.Detachable<ProjectBean> implements ChangeListener {

    private ModelProjectList model = new ModelProjectList(new ArrayList());

    public SystemFactory(ModelProjectList model) {
        this.model = model;
        this.model.addChangeListener(this);
    }
    

    @Override
    protected boolean createKeys(List<ProjectBean> toPopulate) {
        toPopulate.addAll(model.list());
        return true;
    }

    @Override
    protected Node createNodeForKey(ProjectBean projectBean) {
        ProjectNode node = null;
        try {
            
            ProjectObjectMgr projectObjectMgr = Lookups.forPath("Project").lookupAll(ProjectObjectMgr.class).iterator().next();
            projectObjectMgr.setupProjectInfo(projectBean);
            node = new ProjectNode(projectBean);
            
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

    public void add(ProjectBean bean) {
        this.model.add(bean);
        refresh(true);
    }

    public void removeChild(ProjectBean bean) {
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
