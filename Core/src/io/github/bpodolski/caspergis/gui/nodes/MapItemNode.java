/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapElementBean;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapItemNode  extends BeanNode<MapElementBean>{
    
    public MapItemNode(MapElementBean bean) throws IntrospectionException {
        super(bean);
    }
    
}
