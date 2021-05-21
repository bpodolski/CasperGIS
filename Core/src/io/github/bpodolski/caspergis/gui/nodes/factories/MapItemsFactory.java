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
import io.github.bpodolski.caspergis.utils.CgUtils;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    private final MapElementBean mapElementBean;

    private final List<MapElementBean> mapElementBeanList = new ArrayList<>();

    public MapItemsFactory(MapBean mapBean) {
        this.mapBean = mapBean;
        this.mapItemsGetterService = Lookup.getDefault().lookup(MapItemsGetter.class);

        mapElementBeanList.addAll(mapItemsGetterService.getMapItems(mapBean));

        mapElementBean = new MapElementBean(mapElementBeanList, "root");

        CgUtils.io.getOut().println("mapElementBeanList.size = " + mapElementBeanList.size());

        Iterator<MapElementBean> it = mapElementBeanList.iterator();
        while (it.hasNext()) {
            CgUtils.io.getOut().println("mapElementBeanList.name = " + it.next().getName());
        }

//        mapElementBeanList.addAll();
    }

    public MapItemsFactory(MapElementBean mapElementBean) {
        this.mapElementBean = mapElementBean;
        this.mapBean = null;
        this.mapItemsGetterService = null;//Lookup.getDefault().lookup(MapItemsGetter.class);
        mapElementBeanList.addAll(mapElementBean.getMapElementBeans());

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
                node = new MapItemNode((MapElementBean) key, new MapItemsFactory(key));
            }
            if (key.getBeanType() == BeanType.GROUP) {
                node = new MapItemNode((MapElementBean) key, new MapItemsFactory(key));
            }

        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

    public void reorder(int[] perm) {

        MapElementBean[] reordered = new MapElementBean[mapElementBeanList.size()];

        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            MapElementBean c = (MapElementBean) mapElementBeanList.get(i);
            reordered[j] = c;
        }
        mapElementBeanList.clear();
        mapElementBeanList.addAll(Arrays.asList(reordered));
        refresh(true);

    }

    public void add(MapElementBean baseBean) {
        mapElementBeanList.add(baseBean);
        refresh(true);
    }

    public void removeChild(MapElementBean bean) {
        mapElementBeanList.remove(bean);
        refresh(true);

    }

}
