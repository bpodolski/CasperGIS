/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.model;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ModelProjectList {

    private final List<ProjectBean> projectBeans;
    private final ChangeSupport cs = new ChangeSupport(this);

    public ModelProjectList() {
        this(new ArrayList<ProjectBean>());
    }

    public ModelProjectList(List<ProjectBean> projectBeans) {
        this.projectBeans = projectBeans;
    }

    public List<? extends ProjectBean> list() {
        return projectBeans;
    }

    public void add(ProjectBean c) {
        c.setPosition(projectBeans.size() + 1);
        projectBeans.add(c);
        cs.fireChange();
    }

    public void add(int index, ProjectBean c) {
        projectBeans.add(index, c);
        cs.fireChange();
    }

    public void addAll(int index, List bpaList) {
        projectBeans.addAll(index, bpaList);
        cs.fireChange();
    }

    public void remove(ProjectBean c) {
        projectBeans.remove(c);
        cs.fireChange();
    }

    public void reorder(int[] perm) {
        ProjectBean[] reordered = new ProjectBean[projectBeans.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            ProjectBean c = projectBeans.get(i);
            reordered[j] = c;
        }
        projectBeans.clear();
        projectBeans.addAll(Arrays.asList(reordered));
        cs.fireChange();
    }

    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }
}
