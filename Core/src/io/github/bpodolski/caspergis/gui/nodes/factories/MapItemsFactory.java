/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.BeanType;
import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.gui.nodes.MapItemModel;
import io.github.bpodolski.caspergis.gui.nodes.MapItemNode;
import io.github.bpodolski.caspergis.services.MapItemsGetter;
import io.github.bpodolski.caspergis.utils.CgUtils;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.nodes.NodeEvent;
import org.openide.nodes.NodeListener;
import org.openide.nodes.NodeMemberEvent;
import org.openide.nodes.NodeReorderEvent;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapItemsFactory extends ChildFactory.Detachable<MapElementBean>
        implements LookupListener, NodeListener, ChangeListener {
    
    private final MapBean mapBean;
    private final MapItemsGetter mapItemsGetterService;
    private final MapElementBean mapElementBean;    
    private final MapItemModel mapItemModel;

    
    public MapItemsFactory(MapBean mapBean) {
        this.mapBean = mapBean;
        this.mapItemsGetterService = Lookup.getDefault().lookup(MapItemsGetter.class);
        
        this.mapItemModel = new MapItemModel(mapItemsGetterService.getMapItems(mapBean));        
        mapElementBean    = new MapElementBean((List<MapElementBean>) this.mapItemModel.list(), "root");
        
    }
    
    public MapItemsFactory(MapElementBean mapElementBean) {
        this.mapElementBean = mapElementBean;
        this.mapBean = null;
        this.mapItemsGetterService = null;

        this.mapItemModel = new MapItemModel(mapElementBean.getMapElementBeans());
        
    }
    
    @Override
    protected boolean createKeys(List<MapElementBean> list) {
        list.addAll(this.mapItemModel.list());
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

    public MapItemModel getMapItemModel() {
        return mapItemModel;
    }
    

    
    public void add(MapElementBean baseBean) {
      
        this.mapItemModel.add(baseBean);

        refresh(true);
    }
    
    public void removeChild(MapElementBean bean) {
        this.mapItemModel.remove(bean);
        refresh(true);
        
    }
    
    @Override
    protected void addNotify() {
        mapItemModel.addChangeListener(this);
    }
    
    @Override
    protected void removeNotify() {
        mapItemModel.removeChangeListener(this);
    }
    
    @Override
    public void resultChanged(LookupEvent ev) {
        // when Lookup changes, call createKeys again:
        refresh(true);
    }
    
    @Override
    public void childrenAdded(NodeMemberEvent ev) {
        refresh(true);
    }
    
    @Override
    public void childrenRemoved(NodeMemberEvent ev) {
        refresh(true);
    }
    
    @Override
    public void childrenReordered(NodeReorderEvent ev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void nodeDestroyed(NodeEvent ev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        refresh(true);
    }
    
}
