/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.gui.nodes.MapItemNode;
import io.github.bpodolski.caspergis.services.MapItemsGetter;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapItemsFactory extends ChildFactory<MapElementBean> {

    private final MapBean mapBean;
    private final MapItemsGetter mapItemsGetterService;

    private final List<MapElementBean> mapElementBeanList = new ArrayList<>();

    public MapItemsFactory(MapBean mapBean) {
        this.mapBean = mapBean;
        this.mapItemsGetterService = Lookup.getDefault().lookup(MapItemsGetter.class);
        mapElementBeanList.addAll(mapItemsGetterService.getMapItems(mapBean));

    }

    @Override
    protected boolean createKeys(List<MapElementBean> list) {
        list.addAll(this.mapElementBeanList);
        return true;
    }
    
    @Override
    protected Node createNodeForKey(MapElementBean key) {
        BeanNode node = null;

        try {
            if (key.getBeanType() == BeanType.LAYER) {
                node = new MapItemNode((MapElementBean) key);
            }
            if (key.getBeanType() == BeanType.GROUP) {
                node = new MapItemNode((MapElementBean) key);
            }

        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

}
