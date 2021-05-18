/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.PrintoutBean;
import io.github.bpodolski.caspergis.gui.MapDisplayerTopComponent;
import io.github.bpodolski.caspergis.gui.PrintoutTopComponent;
import java.beans.IntrospectionException;
import java.util.Set;
import javax.swing.Action;
import org.openide.actions.OpenAction;
import org.openide.cookies.OpenCookie;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class PrintoutNode extends BeanNode<PrintoutBean> {

    public PrintoutNode(PrintoutBean printoutBean) throws IntrospectionException {
        this(printoutBean, new InstanceContent());
    }

    PrintoutNode(PrintoutBean printoutBean, InstanceContent ic) throws IntrospectionException {
        super(printoutBean, Children.LEAF, new AbstractLookup(ic));
        ic.add(new OpenCookie() {
            @Override
            public void open() {
                TopComponent tc = findTopComponent(printoutBean);
                if (tc == null) {
                    tc = new PrintoutTopComponent(printoutBean);
                    tc.open();
                }
                tc.requestActive();
            }
        });
        setDisplayName(printoutBean.getDisplayName());
        setIconBaseWithExtension("io/github/bpodolski/caspergis/res/printout.png");

    }

    @Override
    public Action getPreferredAction() {
        return null;
    }
    
        private TopComponent findTopComponent(PrintoutBean printoutBean) {
        Set<TopComponent> openTopComponents = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : openTopComponents) {
            if (tc.getLookup().lookup(PrintoutBean.class) == printoutBean) {
                return tc;
            }
        }
        return null;
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{SystemAction.get(OpenAction.class)};
    }
}
