/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.project.dao;

import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.project.datamodel.*;
import java.io.File;
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.netbeans.api.io.IOProvider;
import org.netbeans.api.io.InputOutput;

/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class JpaProjectDbDAO {

    private final SessionFactory sessionFactory;
    private CgProjectInfo cgProjectInfo;

    public JpaProjectDbDAO(String path) {
        System.out.println(path);
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
        configuration.addAnnotatedClass(CgMapLob.class);
        configuration.addAnnotatedClass(CgStyle.class);
        configuration.addAnnotatedClass(CgProjectInfo.class);
        configuration.addAnnotatedClass(CgPrintout.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    public void dispose() {

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
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    public void setProjectInfo() {
        Session session = this.sessionFactory.openSession();
        List<CgProjectInfo> listProjects;
        listProjects = session.createQuery("from CgProjectInfo", CgProjectInfo.class).list();
        session.close();
        if (listProjects.isEmpty()) {
            this.cgProjectInfo = null;
        }
        this.cgProjectInfo = listProjects.get(0);
        session.close();

    }

    public CgProjectInfo getProjectInfo() {
        return cgProjectInfo;

    }

    public List<CgMap> getCgMaps() {
        List<CgMap> listMaps;
        try (Session session = this.sessionFactory.openSession()) {
            listMaps = session.createQuery("from CgMap", CgMap.class).list();
        }
        return listMaps;
    }
}
