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
package io.github.bpodolski.caspergis.beans;;

import io.github.bpodolski.caspergis.utils.BeanType;
import java.util.ArrayList;
//import org.geotools.map.MapContent;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapBean  extends ProjectElementBean{

    private ArrayList elementList = new ArrayList();

    public MapBean(ArrayList bpaList) {
       this(bpaList, "Layers");
    }
    
    public MapBean(ArrayList bpaList, String name) {
        this.setName(name);
        this.setDisplayName(name);
        this.elementList = bpaList;
        this.setBeanType(BeanType.MAP);
    }
    
    public ArrayList getElementList() {
        return elementList;
    }

    public void setElementList(ArrayList elementList) {
        this.elementList = elementList;
    }

   
    
}
