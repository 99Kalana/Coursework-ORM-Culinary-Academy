package org.example.coursework_orm.config;

import org.example.coursework_orm.entity.Admin;
import org.example.coursework_orm.entity.Admission_Coordinator;
import org.example.coursework_orm.entity.Culinary_Programs;
import org.example.coursework_orm.entity.Students;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().addAnnotatedClass(Students.class).addAnnotatedClass(Culinary_Programs.class).addAnnotatedClass(Admission_Coordinator.class).addAnnotatedClass(Admin.class);
        sessionFactory =  configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null)? factoryConfiguration = new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
