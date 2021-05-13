/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapBean;
import java.awt.Component;
import java.beans.IntrospectionException;
import javax.swing.Action;
import javax.swing.JLabel;
import org.openide.nodes.BeanNode;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapNode extends BeanNode<MapBean> {

    public MapNode(MapBean bean) throws IntrospectionException {
        super(bean);
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/map.png");

    }

    @Override
    public Action getPreferredAction() {
        return null;
    }
}
