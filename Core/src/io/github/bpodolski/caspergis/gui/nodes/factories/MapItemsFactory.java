/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.gui.nodes.MapItemNode;
import io.github.bpodolski.caspergis.model.ModelMapElementsList;
import io.github.bpodolski.caspergis.services.MapitemListMgr;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
public class MapItemsFactory extends ChildFactory.Detachable<MapitemBean> {
    
    private final MapBean mapBean; //when paren is map
    private final MapitemListMgr mapitemListMgr;
    private List<MapitemBean> mapItemsList = new ArrayList();
    
    private ModelMapElementsList model = new ModelMapElementsList();
    
    public MapItemsFactory(MapBean mapBean) {
        this.mapBean = mapBean;
        
        Collection<? extends MapitemListMgr> srvList = Lookups.forPath("Project").lookupAll(MapitemListMgr.class);
        if (srvList.iterator().hasNext()) {
            this.mapitemListMgr = srvList.iterator().next();
        } else {
            this.mapitemListMgr = MapitemListMgr.getDefault();
        }
        mapItemsList.addAll(mapitemListMgr.getMapItemsList(mapBean));
//model.
    }
    
    @Override
    protected boolean createKeys(List<MapitemBean> list) {
        list.addAll(mapItemsList);
        return true;
    }
    
    @Override
    protected Node createNodeForKey(MapitemBean bean) {
        BeanNode node = null;
        if (bean.getBeanType() == BeanType.LAYER) {
            try {
                node = new MapItemNode((MapitemBean) bean, Children.LEAF, Lookups.singleton(bean), this);
                
            } catch (IntrospectionException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return node;
    }
    
    public void add(MapitemBean bean) {
        mapItemsList.add(bean);
        refresh(true);
    }
    
    public void add(int index, MapitemBean bean) {
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
    
    public void removeChild(MapitemBean bean) {
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
        MapitemBean[] reordered = new MapitemBean[this.mapItemsList.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            MapitemBean c = (MapitemBean) this.mapItemsList.get(i);
            reordered[j] = c;
        }
        this.mapItemsList.clear();
        this.mapItemsList.addAll(Arrays.asList(reordered));
        refresh(true);
        
    }
    
}
