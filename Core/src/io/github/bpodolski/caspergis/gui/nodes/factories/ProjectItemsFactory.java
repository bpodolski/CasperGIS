/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.nodes.factories;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.beans.ProjectElementBean;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.ChildFactory;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectItemsFactory extends ChildFactory<ProjectElementBean> {

    private ProjectBean projectBean;
//    private MapGetterService mapGetterService;
//    private PrintoutGetterService printoutGetterService;
    private List<ProjectElementBean> projectElementList = new ArrayList<ProjectElementBean>();

    public ProjectItemsFactory(ProjectBean projectBean) {
        this.projectBean = projectBean;
//        this.mapGetterService = Lookup.getDefault().lookup(MapGetterService.class);
//        this.printoutGetterService = Lookup.getDefault().lookup(PrintoutGetterService.class);
////
////        projectElementList.addAll(null) ;//= mapGetterService.getMapList(projectBean);
////        projectElementList.addAll(null) ;//= printoutGetterService.getPrintoutList(projectBean);


    }

    @Override
    protected boolean createKeys(List<ProjectElementBean> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
