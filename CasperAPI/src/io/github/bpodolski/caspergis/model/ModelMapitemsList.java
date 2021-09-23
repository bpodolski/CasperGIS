/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.model;

import io.github.bpodolski.caspergis.beans.MapitemBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ModelMapitemsList {

    private final List<MapitemBean> elementMapBeans;
    private final ChangeSupport cs = new ChangeSupport(this);

    public ModelMapitemsList() {
        this(new ArrayList<MapitemBean>());
    }

    public ModelMapitemsList(List<MapitemBean> elementMapBeans) {
        this.elementMapBeans = elementMapBeans;
    }

    public List<MapitemBean> list() {
        return elementMapBeans;
    }

    public void add(MapitemBean c) {
//        c.setPosition(elementMapBeans.size() + 1);
        elementMapBeans.add(c);
        cs.fireChange();
    }

    public void addAll(List<MapitemBean> mapitemBean) {
        elementMapBeans.addAll(mapitemBean);
        cs.fireChange();
    }

    public void addAll(int index, List<MapitemBean> mapitemBean) {
        elementMapBeans.addAll(index, mapitemBean);
        cs.fireChange();
    }

    public void remove(MapitemBean c) {
        elementMapBeans.remove(c);
        cs.fireChange();
    }

    public void reorder(int[] perm) {
        MapitemBean[] reordered = new MapitemBean[elementMapBeans.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            MapitemBean c = elementMapBeans.get(i);
            reordered[j] = c;
        }
        elementMapBeans.clear();
        elementMapBeans.addAll(Arrays.asList(reordered));
        cs.fireChange();
    }

    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }

}
