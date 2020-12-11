/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util.bd;

import java.sql.Connection;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionImpl;
import org.hibernate.type.Type;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static Logger logger;

    static {
        try {
            iniciar();
        } catch (Throwable ex) {
            ex.printStackTrace();
            //  logger.error("error al Iniciando Log people force", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void iniciar() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addResource("com/commerce/mapper/xml/MapaMapper.hbm.xml");
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                iniciar();
            }
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }

    public static Connection getConnectionHibernate() {
        session = sessionFactory.openSession();
        return ((SessionImpl) session).connection();
    }

    public static void closeConnectionHibernate() {
        if (session.isOpen()) {
            session.close();
        }
    }

    public static SQLQuery addScalar(SQLQuery query, String string, Type type) {
        return query.addScalar(string, type);
    }

    public static String crearParametros(Integer count) {
        String para = "";
        for (int i = 0; i < count; i++) {
            para = para + (i > 0 ? ",?" : "?");
        }
        return para;
    }

    public static String crearParametros(String[] parametros) {
        String para = "";
        int i = 0;
        for (String obj : parametros) {
            para = para + (i++ > 0 ? ",:" + obj : ":" + obj);
        }
        return para;
    }
}
