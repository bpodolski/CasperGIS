/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.system.dao;


import io.github.bpodolski.caspergis.api.CasperInfo;
import io.github.bpodolski.caspergis.system.datamodel.CgProject;
import java.io.File;
import java.util.ArrayList;
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
import org.openide.util.NbPreferences;



/**
 *
 * @author Bartłomiej Podolski <bartp@poczta.fm>
 */
public class JpaSystemDbDAO {

    private SessionFactory sessionFactory;
//    private Session session;

    InputOutput io = IOProvider.getDefault().getIO("Output", false);

    public JpaSystemDbDAO() {
        String DB_SYSTEM_PATH = NbPreferences.forModule(CasperInfo.class).get(CasperInfo.DB_SYSTEM_PATH,"");
        
        File f = new File(DB_SYSTEM_PATH);
        boolean create = !f.exists();
        
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
        properties.put("hibernate.connection.url", "jdbc:sqlite:" + CasperInfo.DB_SYSTEM_PATH);
        properties.put("hibernate.dialect", "pl.com.caspergis.hibernate.SQLiteDialect");

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

        configuration.addAnnotatedClass(CgProject.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//        if (create) {
//            InitDb();
//        }
    }

    public List<CgProject> getProjects() {
        Session session = this.sessionFactory.openSession();
        List<CgProject> listProjects;
        listProjects = session.createQuery("from CgProject", CgProject.class).list();
        session.close();
        return listProjects;

    }

    public void dispose() {

        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

//    private void InitDb() {
//        ArrayList list = (ArrayList) getProjects();
//        if (list.isEmpty()) {
//            CgProject project = new CgProject();
//            project.setName("Projekt podstawowy");
//            project.setPath(CasperInfo.DB_DEFAULT_PROJECT_PATH);
//
//            saveProject(project);
//        }
//
//    }

    public void saveProject(CgProject project) {
        Session session = this.sessionFactory.openSession();
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
        session.close();

    }
}
