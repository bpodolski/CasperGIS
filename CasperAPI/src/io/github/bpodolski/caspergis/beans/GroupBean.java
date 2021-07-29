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

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class GroupBean extends ElementMapBean {

    private int transparency = 0;
    private boolean visible = true;
    
       public GroupBean(String name) {
        super(null, name);
        this.setBeanType(BeanType.GROUP);        
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
    
}
