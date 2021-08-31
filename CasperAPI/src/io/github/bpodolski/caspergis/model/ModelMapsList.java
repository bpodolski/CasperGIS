/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.model;

import io.github.bpodolski.caspergis.beans.MapBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ModelMapsList {

    private final List<MapBean> mapBeans;
    private final ChangeSupport cs = new ChangeSupport(this);

    public ModelMapsList() {
        this(new ArrayList<MapBean>());
    }

    public ModelMapsList(List<MapBean> mapBeans) {
        this.mapBeans = mapBeans;
    }

    public List<? extends MapBean> list() {
        return mapBeans;
    }

    public void add(MapBean c) {
        c.setPosition(mapBeans.size() + 1);
        mapBeans.add(c);
        cs.fireChange();
    }

    public void remove(MapBean c) {
        mapBeans.remove(c);
        cs.fireChange();
    }

    public void reorder(int[] perm) {
        MapBean[] reordered = new MapBean[mapBeans.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            MapBean c = mapBeans.get(i);
            reordered[j] = c;
        }
        mapBeans.clear();
        mapBeans.addAll(Arrays.asList(reordered));
        cs.fireChange();
    }

    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }

    public void addAll(List<MapBean> mapList) {
       mapBeans.addAll(mapList);
    }
}
