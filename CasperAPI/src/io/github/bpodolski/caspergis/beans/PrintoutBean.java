/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.beans;

import java.util.ArrayList;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class PrintoutBean extends ElementProjectBean{
         
   private ArrayList<ElementProjectBean> projectElementBeans;


    public PrintoutBean(ArrayList<ElementProjectBean> projectElementBeans) {
       this(projectElementBeans, "Printouts");
       this.setBeanType(BeanType.PRINTOUT);
    }
    
    public PrintoutBean(ArrayList<ElementProjectBean> projectElementBeans, String name) {
        this.setName(name);
        this.setDisplayName(name);
        this.projectElementBeans = projectElementBeans;
        this.setBeanType(BeanType.PRINTOUT);
    }

    
}
