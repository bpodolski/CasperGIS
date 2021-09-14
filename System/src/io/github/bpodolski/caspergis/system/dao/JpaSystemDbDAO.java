/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.dao;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.system.datamodel.CgSysProject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.openide.util.NbPreferences;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public final class JpaSystemDbDAO {

    private final SessionFactory sessionFactory;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    private boolean daoReady = false;

    public static final String DAO_READY = "daoReady";

    /**
     * Get the value of daoReady
     *
     * @return the value of daoReady
     */
    public boolean isDaoReady() {
        return daoReady;
    }

    /**
     * Set the value of daoReady
     *
     * @param daoReady new value of daoReady
     */
    public void setDaoReady(boolean daoReady) {
        boolean oldDaoReady = this.daoReady;
        this.daoReady = daoReady;
        propertyChangeSupport.firePropertyChange(DAO_READY, oldDaoReady, daoReady);
    }


    public JpaSystemDbDAO() {
        String DB_SYSTEM_PATH = NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_SYSTEM_PATH, "");
        daoReady = false;

        File f = new File(DB_SYSTEM_PATH);
        boolean create = !f.exists();

        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
        properties.put("hibernate.connection.url", "jdbc:sqlite:" + f.getPath());
        properties.put("hibernate.dialect", "io.github.bpodolski.caspergis.lib.hibernate.SQLiteDialect");

        properties.put("hibernate.connection.charSet", "UTF-8");

        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");

        if (!create) {
            properties.put("hibernate.hdm2ddl.auto", "update");
        } else {
            properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        }

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(CgSysProject.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        this.setDaoReady(true);//sessionFactory.isOpen());

    }

    public List<CgSysProject> getProjects() {
        List<CgSysProject> listProjects;
        try (Session session = this.sessionFactory.openSession()) {
            listProjects = session.createQuery("from CgSysProject", CgSysProject.class).list();
        }
        return listProjects;

    }

    public void dispose() {
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void saveProject(CgSysProject project) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(project);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }    }

    public void deleteProject(CgSysProject project) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.delete(project);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
