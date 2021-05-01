/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes;

import io.github.bpodolski.caspergis.beans.PrintoutBean;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class PrintoutNode   extends BeanNode<PrintoutBean>{
    
    public PrintoutNode(PrintoutBean bean) throws IntrospectionException {
        super(bean);
    }
    
}
