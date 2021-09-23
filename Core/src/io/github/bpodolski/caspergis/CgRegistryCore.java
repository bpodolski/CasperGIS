/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis;

import io.github.bpodolski.caspergis.beans.MapBean;
import io.github.bpodolski.caspergis.beans.MapitemBean;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.gui.nodes.factories.MapitemsFactory;
import io.github.bpodolski.caspergis.gui.nodes.factories.ProjectitemsFactory;
import io.github.bpodolski.caspergis.gui.nodes.services.CoreMapExplorerManagerMgr;
import io.github.bpodolski.caspergis.model.ModelMapsList;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import java.util.HashMap;
import org.openide.explorer.ExplorerManager;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class CgRegistryCore {

    public static ModelProjectList modelProjectList;
    public static final HashMap<ProjectBean, ProjectitemsFactory> projectItemsFactoryMap = new HashMap<ProjectBean, ProjectitemsFactory>();
    
    public static HashMap<ProjectBean, ModelMapsList> modelMapsList = new HashMap<ProjectBean, ModelMapsList>();
    public static final HashMap explorerManagerMap = new HashMap<MapBean, ExplorerManager>();
    
    public static final CoreMapExplorerManagerMgr coreMapExplorerManager = new CoreMapExplorerManagerMgr();
    public static final HashMap mapExplorerManagers = new HashMap<MapBean, ExplorerManager>();

    public static final HashMap<MapitemBean, MapitemsFactory> mapItemsFactoryMap = new HashMap<MapitemBean, MapitemsFactory>();
    

}
