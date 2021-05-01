/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.gui.nodes.factories.ProjectItemsFactory;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectNode  extends BeanNode<ProjectBean> {
    
    public ProjectNode(ProjectBean bean, ProjectItemsFactory factory) throws IntrospectionException {
        super(bean, Children.create(factory, true), Lookups.singleton(bean));
////        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/icons/layer.png");
    }
    
}
