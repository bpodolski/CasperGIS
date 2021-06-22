/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

import java.awt.datatransfer.DataFlavor;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapElementFlavor  extends DataFlavor {
    public static final DataFlavor MAPELEMENT_FLAVOR = new MapElementFlavor();

    public MapElementFlavor() {
        super(MapElementBean.class, "MapElementBean");
    }
}
