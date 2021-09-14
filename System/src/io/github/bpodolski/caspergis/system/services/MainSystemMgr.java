/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.services;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.beans.ProjectBean;
import io.github.bpodolski.caspergis.model.ModelProjectList;
import io.github.bpodolski.caspergis.services.SystemMgr;
import io.github.bpodolski.caspergis.system.dao.JpaSystemDbDAO;
import io.github.bpodolski.caspergis.system.datamodel.CgSysProject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author bpodolski
 */
@ServiceProvider(service = SystemMgr.class, path = "System")
public class MainSystemMgr extends SystemMgr implements PropertyChangeListener {

    private JpaSystemDbDAO dao;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private boolean systemDAOready = false;
    private boolean modelProjectListReady = false;
    
    private final HashMap projectMap = new HashMap<ProjectBean, CgSysProject>();
    
    ModelProjectList mdelProjectList = null;

    @Override
    public void initSystemDAO() {
        dao = new JpaSystemDbDAO();
        dao.addPropertyChangeListener((PropertyChangeListener) this);
        
        
        if (dao.isDaoReady()) {

            List<ProjectBean> projectList = new ArrayList();
            projectList.add(getSystemProject());
            projectList.addAll(getProjectList());
            mdelProjectList = new ModelProjectList(projectList);

        }
    }

    @Override
    public ModelProjectList getModelProjectList() {

        return mdelProjectList;
    }

    @Override
    public boolean isSystemDAOready() {
        return true;//systemDAOready;
    }

    @Override
    public void setSystemDAOready(boolean systemDAOready) {
        boolean oldDaoReady = this.systemDAOready;
        this.systemDAOready = systemDAOready;
        propertyChangeSupport.firePropertyChange("SYSTEM_DAO_READY", oldDaoReady, systemDAOready);
    }

    @Override
    public boolean isModelProjectListReady() {
        return modelProjectListReady;
    }

    @Override
    public void setModelProjectListReady(boolean modelProjectListReady) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals(JpaSystemDbDAO.DAO_READY)) {
            setSystemDAOready((boolean) evt.getNewValue());

        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public ProjectBean getSystemProject() {
        ProjectBean systemProject = new ProjectBean();
        systemProject.setName("SystemTestProject");
        systemProject.setPath(NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_DEFAULT_PROJECT_PATH, ""));

        return systemProject;
    }

    @Override
    public List<ProjectBean> getProjectList() {
        ArrayList<ProjectBean> projectList = new ArrayList<>();
        Iterator<CgSysProject> itr = dao.getProjects().iterator();
        while (itr.hasNext()) {
            CgSysProject cgSysProject = itr.next();
            ProjectBean projectBean = new ProjectBean();

            projectBean.setPath(cgSysProject.getPath());
            projectBean.setPosition(cgSysProject.getPosition());
            projectBean.setHidden(cgSysProject.isHidden());

            projectList.add(projectBean);
            projectMap.put(projectBean, cgSysProject);
        }

        return projectList;
    }

    @Override
    public void addProject(File toAdd) {
        ProjectBean projectBean = new ProjectBean();//            
        projectBean.setName(toAdd.getName());
        projectBean.setPath(toAdd.getPath());

        getModelProjectList().add(projectBean);

        CgSysProject cgSysProject = new CgSysProject();
        cgSysProject.setPath(projectBean.getPath());
        cgSysProject.setPosition(projectBean.getPosition());
        cgSysProject.setHidden(projectBean.isHidden());

        this.dao.saveProject(cgSysProject);
        projectMap.put(projectBean, cgSysProject);

    }

    @Override
    public void newProject(File toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProject(ProjectBean projectBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeProject(ProjectBean projectBean) {

        dao.deleteProject((CgSysProject) projectMap.get(projectBean));
        this.getModelProjectList().remove(projectBean);
    }

    @Override
    public void deleteProject(ProjectBean projectBean) {
        this.closeProject(projectBean);
    }
}
