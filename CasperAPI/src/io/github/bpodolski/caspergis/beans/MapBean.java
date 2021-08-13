/*
 * Copyright (C) 2020 Bartłomiej Podolski <bartp@poczta.fm>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package io.github.bpodolski.caspergis.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
//import org.geotools.map.MapContent;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapBean extends ElementProjectBean {

    private ArrayList<ElementMapBean> mapElementList = new ArrayList();
    boolean active = false;
    private final PropertyChangeSupport propertyChangeSupport;

    public MapBean(ArrayList<ElementMapBean> mapElementList) {
        this(mapElementList, "Layers");
    }

    public MapBean(ArrayList<ElementMapBean> mapElementList, String name) {
        this.setName(name);
        this.setDisplayName(name);
        this.mapElementList = mapElementList;
        this.setBeanType(BeanType.MAP);
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public ArrayList<ElementMapBean> getMapElementList() {
        return mapElementList;
    }

    public void setMapElementList(ArrayList<ElementMapBean> mapElementList) {
        this.mapElementList = mapElementList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, active);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
