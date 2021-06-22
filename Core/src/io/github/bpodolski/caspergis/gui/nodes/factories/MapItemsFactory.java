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
import java.util.Arrays;
import java.util.List;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapItemsFactory extends ChildFactory.Detachable<MapElementBean> {

    private final MapBean mapBean; //when paren is map
    private final MapItemsGetter mapItemsGetterService;
    private List<MapElementBean> mapItemsList;

    public MapItemsFactory(MapBean mapBean) {
        this.mapBean = mapBean;
        this.mapItemsGetterService = Lookup.getDefault().lookup(MapItemsGetter.class);
        mapItemsList = mapItemsGetterService.getMapItems(mapBean);
    }

    @Override
    protected boolean createKeys(List<MapElementBean> list) {
        list.addAll(mapItemsList);
        return true;
    }

    @Override
    protected Node createNodeForKey(MapElementBean bean) {
        BeanNode node = null;
        if (bean.getBeanType() == BeanType.LAYER) {
            try {
                node = new MapItemNode((MapElementBean) bean, Children.LEAF, Lookups.singleton(bean), this);
                
           } catch (IntrospectionException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return node;
    }

    public void add(MapElementBean bean) {
        mapItemsList.add(bean);
        refresh(true);
    }

    public void add(int index, MapElementBean bean) {
        mapItemsList.add(index, bean);
        refresh(true);
    }

    public void addAll(List list) {
        mapItemsList.addAll(list);
        refresh(true);
    }

    public void addAll(int index, List list) {
        mapItemsList.addAll(index, list);
        refresh(true);
    }

    public void removeChild(MapElementBean bean) {
        mapItemsList.remove(bean);
        refresh(true);
    }

    @Override
    protected void addNotify() {
    }

    @Override
    protected void removeNotify() {

    }

    public void reorder(int[] perm) {
        MapElementBean[] reordered = new MapElementBean[this.mapItemsList.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            MapElementBean c = (MapElementBean) this.mapItemsList.get(i);
            reordered[j] = c;
        }
        this.mapItemsList.clear();
        this.mapItemsList.addAll(Arrays.asList(reordered));
        refresh(true);

    }

}
