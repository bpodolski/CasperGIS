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

//import org.geotools.map.Layer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

//import org.geotools.styling.Style;
/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class LayerBean extends MapitemBean {

    private int transparency = 0;
    private boolean visible = true;
    private String connectionStr;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public String getConnectionStr() {
        return connectionStr;
    }

    public void setConnectionStr(String connectionStr) {
        this.connectionStr = connectionStr;
    }

    public LayerBean(String name) {
        this(null, name);

    }

    public LayerBean(List<MapitemBean> mapElementBeans, String name) {
        super(mapElementBeans, name);
        this.setBeanType(BeanType.LAYER);
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
