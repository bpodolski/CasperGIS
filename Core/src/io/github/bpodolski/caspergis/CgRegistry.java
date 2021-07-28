/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapElementBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.gui.nodes.MapNode;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapItemsFactory;
import io.github.bpodolski.caspergis.gui.nodes.factories.ProjectItemsFactory;
import io.github.bpodolski.caspergis.gui.nodes.factories.SystemFactory;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.HashMap;
import org.openide.explorer.ExplorerManager;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class CgRegistry {
    public static final HashMap explorerManagerMap = new HashMap<MapBean, ExplorerManager>();
    
    public static final SystemFactory systemFactory = new SystemFactory();
    public static final HashMap mapItemsFactoryMap = new HashMap<MapElementBean, MapItemsFactory>();
    public static final HashMap projectItemsFactoryMap = new HashMap<ProjectBean, ProjectItemsFactory>();
    
    
    private MapBean activeMapBean;

    public static final String PROP_ACTIVEMAPBEAN = "activeMapBean";

    /**
     * Get the value of activeMapBean
     *
     * @return the value of activeMapBean
     */
    public MapBean getActiveMapBean() {
        return activeMapBean;
    }

    /**
     * Set the value of activeMapBean
     *
     * @param activeMapBean new value of activeMapBean
     */
    public void setActiveMapBean(MapBean activeMapBean) {
        MapBean oldActiveMapBean = this.activeMapBean;
        this.activeMapBean = activeMapBean;
        propertyChangeSupport.firePropertyChange(PROP_ACTIVEMAPBEAN, oldActiveMapBean, activeMapBean);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private transient final VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /**
     * Add VetoableChangeListener.
     *
     * @param listener
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Remove VetoableChangeListener.
     *
     * @param listener
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    private MapNode activeMapNode = null;

    public static final String PROP_ACTIVEMAPNODE = "activeMapNode";

    /**
     * Get the value of activeMapNode
     *
     * @return the value of activeMapNode
     */
    public MapNode getActiveMapNode() {
        return activeMapNode;
    }

    /**
     * Set the value of activeMapNode
     *
     * @param activeMapNode new value of activeMapNode
     * @throws java.beans.PropertyVetoException
     */
    public void setActiveMapNode(MapNode activeMapNode) throws PropertyVetoException {
        MapNode oldActiveMapNode = this.activeMapNode;
        vetoableChangeSupport.fireVetoableChange(PROP_ACTIVEMAPNODE, oldActiveMapNode, activeMapNode);
        this.activeMapNode = activeMapNode;
        propertyChangeSupport.firePropertyChange(PROP_ACTIVEMAPNODE, oldActiveMapNode, activeMapNode);
    }

    
    
}
