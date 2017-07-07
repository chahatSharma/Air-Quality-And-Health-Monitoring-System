package com.neu.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import com.neu.business.AuditableInterceptor;

public class DAO {
	
	private static final Logger log = Logger.getAnonymousLogger();
	
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal sessionThread = new ThreadLocal();
	
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    protected DAO() {
    }

    public static Session getSession()
    {
        Session session = (Session) DAO.sessionThread.get();
        
        if (session == null)
        {
        	log.log(Level.INFO, "inside session");
        	AuditableInterceptor interceptor = new AuditableInterceptor();
            session = sessionFactory.openSession(interceptor);
            interceptor.setSession(session);
            DAO.sessionThread.set(session);
        }
        log.log(Level.INFO, "outside session");
      
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.sessionThread.set(null);
    }

    public static void close() {
        getSession().close();
        DAO.sessionThread.set(null);
    }
}
