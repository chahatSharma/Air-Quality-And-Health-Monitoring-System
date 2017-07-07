package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Symptomsreported.
 * @see com.reverse.generated.Symptomsreported
 * @author Hibernate Tools
 */
@Stateless
public class SymptomsreportedHome {

	private static final Log log = LogFactory.getLog(SymptomsreportedHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Symptomsreported transientInstance) {
		log.debug("persisting Symptomsreported instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Symptomsreported persistentInstance) {
		log.debug("removing Symptomsreported instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Symptomsreported merge(Symptomsreported detachedInstance) {
		log.debug("merging Symptomsreported instance");
		try {
			Symptomsreported result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Symptomsreported findById(long id) {
		log.debug("getting Symptomsreported instance with id: " + id);
		try {
			Symptomsreported instance = entityManager.find(Symptomsreported.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
