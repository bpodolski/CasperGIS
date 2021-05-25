/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapElementBean;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class MapItemModel {

    private final List<MapElementBean> mapElementBeans;
    private final ChangeSupport cs = new ChangeSupport(this);

    public MapItemModel(List<MapElementBean> MapElementBeans) {
        this.mapElementBeans = MapElementBeans;
    }

    public void add(MapElementBean bean) {
        mapElementBeans.add(bean);
    }

    public void remove(MapElementBean bean) {
        mapElementBeans.remove(bean);
    }

    public List<? extends MapElementBean> list() {
        return mapElementBeans;
    }

    public void reorder(int[] perm) {
        MapElementBean[] reordered = new MapElementBean[mapElementBeans.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            MapElementBean c = mapElementBeans.get(i);
            reordered[j] = c;
        }
        mapElementBeans.clear();
        mapElementBeans.addAll(Arrays.asList(reordered));
        cs.fireChange();
    }

    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }
}
