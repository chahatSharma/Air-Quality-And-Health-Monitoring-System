package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Airquality.
 * @see com.reverse.generated.Airquality
 * @author Hibernate Tools
 */
@Stateless
public class AirqualityHome {

	private static final Log log = LogFactory.getLog(AirqualityHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Airquality transientInstance) {
		log.debug("persisting Airquality instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Airquality persistentInstance) {
		log.debug("removing Airquality instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Airquality merge(Airquality detachedInstance) {
		log.debug("merging Airquality instance");
		try {
			Airquality result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Airquality findById(long id) {
		log.debug("getting Airquality instance with id: " + id);
		try {
			Airquality instance = entityManager.find(Airquality.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
