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

import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;
//import org.geotools.map.MapContent;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapBean extends ProjectitemBean {

    private ArrayList<MapitemBean> mapElementList = new ArrayList();
    boolean active = false;
    private final ChangeSupport cs = new ChangeSupport(this);

    public MapBean(ArrayList<MapitemBean> mapElementList) {
        this(mapElementList, "Layers");
    }

    public MapBean(ArrayList<MapitemBean> mapElementList, String name) {
        this.setName(name);
        this.setDisplayName(name);
        this.mapElementList = mapElementList;
        this.setBeanType(BeanType.MAP);
    }

    public ArrayList<MapitemBean> getMapElementList() {
        return mapElementList;
    }

    public void setMapElementList(ArrayList<MapitemBean> mapElementList) {
        this.mapElementList = mapElementList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        cs.fireChange();

    }

    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }

}
