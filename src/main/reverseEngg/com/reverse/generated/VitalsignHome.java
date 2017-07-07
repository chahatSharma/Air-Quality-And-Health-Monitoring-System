package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Vitalsign.
 * @see com.reverse.generated.Vitalsign
 * @author Hibernate Tools
 */
@Stateless
public class VitalsignHome {

	private static final Log log = LogFactory.getLog(VitalsignHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Vitalsign transientInstance) {
		log.debug("persisting Vitalsign instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Vitalsign persistentInstance) {
		log.debug("removing Vitalsign instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Vitalsign merge(Vitalsign detachedInstance) {
		log.debug("merging Vitalsign instance");
		try {
			Vitalsign result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Vitalsign findById(long id) {
		log.debug("getting Vitalsign instance with id: " + id);
		try {
			Vitalsign instance = entityManager.find(Vitalsign.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
