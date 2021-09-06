/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.model.ModelProjectList;
import io.github.bpodolski.caspergis.gui.nodes.factories.SystemFactory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.MoveDownAction;
import org.openide.actions.MoveUpAction;
import org.openide.actions.RenameAction;
import org.openide.actions.ReorderAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
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
    
     @Override
    public Action[] getActions(boolean context) {
        ArrayList actList = new ArrayList();
        
        ReorderAction reorderAction = SystemAction.get(ReorderAction.class);
//        MoveUpAction moveUpAction = SystemAction.get(MoveUpAction.class);
//        MoveDownAction moveDownAction = SystemAction.get(MoveDownAction.class);
//        RenameAction renameAction = SystemAction.get(RenameAction.class);

        actList.add(reorderAction);
        return (Action[]) actList.toArray(new Action[actList.size()]);
    }

}
