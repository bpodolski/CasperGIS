/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.model.ModelProjectList;
import io.github.bpodolski.caspergis.gui.nodes.factories.SystemFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectsRootNode extends AbstractNode {

    InstanceContent ic;
    
   
    public ProjectsRootNode(ModelProjectList model) {
        this(model, new InstanceContent());
    }

    public ProjectsRootNode(ModelProjectList model, InstanceContent ic) {
         super(Children.create(new SystemFactory(model), true), new AbstractLookup(ic));
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
                model.reorder(perm);
            }
        });
        
    }

}
