package com.zenika.addressbook.gwt.server.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryHolder {

    private static final SessionFactory sessionFactory;

    static {
        try {
        	sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
