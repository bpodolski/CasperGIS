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
import java.util.List;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ElementMapBean {

    private List<ElementMapBean> mapElementBeans;
    
    

    private BeanType beanType = BeanType.MAP_ELEMENT;
    private String name = "Map Element";
    private String displayName = "Map Element";

    public ElementMapBean(List<ElementMapBean> mapElementBeans, String name) {
        this.mapElementBeans = mapElementBeans;
        this.name = name;
    }

    public BeanType getBeanType() {
        return beanType;
    }

    public void setBeanType(BeanType beanType) {
        this.beanType = beanType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<ElementMapBean> getMapElementBeans() {
        if (mapElementBeans == null) {
            mapElementBeans = new ArrayList<>();
        }
        return mapElementBeans;
    }

}
