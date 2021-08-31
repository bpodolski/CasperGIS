/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapItemsFactory;
import io.github.bpodolski.caspergis.gui.nodes.factories.ProjectItemsFactory;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import java.util.HashMap;
import org.openide.explorer.ExplorerManager;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class CgRegistry {

    public static final HashMap explorerManagerMap = new HashMap<MapBean, ExplorerManager>();

    public static ModelProjectList modelProjectList;
    public static HashMap<ProjectBean, ModelMapsList> modelMapsList = new HashMap<ProjectBean, ModelMapsList>();

    public static final HashMap<MapitemBean, MapItemsFactory> mapItemsFactoryMap = new HashMap<MapitemBean, MapItemsFactory>();

    public static final HashMap<ProjectBean, ProjectItemsFactory> projectItemsFactoryMap = new HashMap<ProjectBean, ProjectItemsFactory>();

}
