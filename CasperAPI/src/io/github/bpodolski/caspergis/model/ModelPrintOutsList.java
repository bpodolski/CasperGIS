/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.model;

import io.github.bpodolski.caspergis.beans.PrintoutBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ModelPrintOutsList {

    private final List<PrintoutBean> printOutBeans;
    private final ChangeSupport cs = new ChangeSupport(this);

    public ModelPrintOutsList() {
        this(new ArrayList<PrintoutBean>());
    }

    public ModelPrintOutsList(List<PrintoutBean> printOutBeans) {
        this.printOutBeans = printOutBeans;
    }

    public List<? extends PrintoutBean> list() {
        return printOutBeans;
    }

    public void add(PrintoutBean c) {
        c.setPosition(printOutBeans.size() + 1);
        printOutBeans.add(c);
        cs.fireChange();
    }

    public void remove(PrintoutBean c) {
        printOutBeans.remove(c);
        cs.fireChange();
    }

    public void reorder(int[] perm) {
        PrintoutBean[] reordered = new PrintoutBean[printOutBeans.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            PrintoutBean c = printOutBeans.get(i);
            reordered[j] = c;
        }
        printOutBeans.clear();
        printOutBeans.addAll(Arrays.asList(reordered));
        cs.fireChange();
    }

    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }
}
