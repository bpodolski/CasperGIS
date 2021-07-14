/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.dao;

import io.github.bpodolski.caspergis.project.datamodel.*;
import java.io.File;
import java.util.List;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class ProjectDAO {

    private SessionFactory sessionFactory;
    private CgProjectInfo cgProjectInfo;

    public static void createDb(String path) {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
        properties.put("hibernate.connection.url", "jdbc:sqlite:" + f.getPath());
        properties.put("hibernate.dialect", "io.github.bpodolski.caspergis.lib.hibernate.SQLiteDialect");
        properties.put("hibernate.connection.charSet", "UTF-8");
        properties.put("hibernate.connection.autocommit", "true"); // dla aplikacji desktopowych opcja zalecana
        properties.put("hibernate.show_sql", "false");//do celów testowych lepsze true
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "false");
        properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(CgGroup.class);
        configuration.addAnnotatedClass(CgLabel.class);
        configuration.addAnnotatedClass(CgLayer.class);
        configuration.addAnnotatedClass(CgMap.class);
        configuration.addAnnotatedClass(CgLayerStyle.class);
        configuration.addAnnotatedClass(CgProjectInfo.class);
        configuration.addAnnotatedClass(CgPrintout.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                CgProjectInfo projectInfo = new CgProjectInfo();
                projectInfo.setName("CasperGIS project");
                CgMap cgMap = new CgMap();
                cgMap.setName("Layers");

                session.save(projectInfo);
                session.save(cgMap);
                transaction.commit();
                session.close();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        sessionFactory.close();
    }

    private void openProject(String path) {
        File f = new File(path);
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
        properties.put("hibernate.connection.url", "jdbc:sqlite:" + f.getPath());

        properties.put("hibernate.dialect", "io.github.bpodolski.caspergis.lib.hibernate.SQLiteDialect");
        properties.put("hibernate.connection.charSet", "UTF-8");

        properties.put("hibernate.connection.autocommit", "true"); // dla aplikacji desktopowych opcja zalecana
        properties.put("hibernate.show_sql", "false");//do celów testowych lepsze true
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "false");

        properties.put("hibernate.hdm2ddl.auto", "update");
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(CgGroup.class);
        configuration.addAnnotatedClass(CgLabel.class);
        configuration.addAnnotatedClass(CgLayer.class);
        configuration.addAnnotatedClass(CgMap.class);
        configuration.addAnnotatedClass(CgLayerStyle.class);
        configuration.addAnnotatedClass(CgProjectInfo.class);
        configuration.addAnnotatedClass(CgPrintout.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public ProjectDAO(String path, boolean create) {
        File f = new File(path);
        if (create) {
            if (f.exists()) {
                f.delete();
            }
            createDb(path);
        }
        openProject(path);
    }

    public void dispose() {
        this.sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void saveProjectInfo(CgProjectInfo cgProjectInfo) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(cgProjectInfo);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void setProjectInfo() {

        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<CgProjectInfo> listProjects;
                listProjects = session.createQuery("from CgProjectInfo", CgProjectInfo.class).list();
                if (listProjects.isEmpty()) {
                    this.cgProjectInfo = new CgProjectInfo();
                    cgProjectInfo.setName("tmp project");
                } else {
                    this.cgProjectInfo = listProjects.get(0);
                }
                transaction.commit();
                session.close();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

    }

    public CgProjectInfo getProjectInfo() {
        return cgProjectInfo;
    }

    public List<CgMap> getCgMaps() {
        List<CgMap> listMaps;

        try (Session session = this.sessionFactory.openSession()) {
            listMaps = session.createQuery("from CgMap", CgMap.class).list();
            session.close();
        }
        return listMaps;
    }

    public List<CgLayer> getLayers(CgMap cgMap) {
        List<CgLayer> cgLayers;
        try (Session session = this.sessionFactory.openSession()) {
   
            Query query = session.createQuery("select b from CgLayer b where b.cgMap = :cgMap").setParameter("cgMap", cgMap);            
            cgLayers = query.getResultList();
            
            session.close();
        }
        return cgLayers;
    }

    public void addMap(String name) {
        CgMap cgMap = new CgMap();
        if (name.equals("")) {
            name = "Layers";
        }
        cgMap.setName(name);
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(cgMap);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void saveMap(CgMap cgMap) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(cgMap);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

}
