/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system;

import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.system.datamodel.CgSysProject;
import java.util.HashMap;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class CgRegistrySystem {
    public static final HashMap projectMap = new HashMap<ProjectBean, CgSysProject>();
}
