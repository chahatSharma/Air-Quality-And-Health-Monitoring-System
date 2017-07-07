package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Symptomslist.
 * @see com.reverse.generated.Symptomslist
 * @author Hibernate Tools
 */
@Stateless
public class SymptomslistHome {

	private static final Log log = LogFactory.getLog(SymptomslistHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Symptomslist transientInstance) {
		log.debug("persisting Symptomslist instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Symptomslist persistentInstance) {
		log.debug("removing Symptomslist instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Symptomslist merge(Symptomslist detachedInstance) {
		log.debug("merging Symptomslist instance");
		try {
			Symptomslist result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Symptomslist findById(long id) {
		log.debug("getting Symptomslist instance with id: " + id);
		try {
			Symptomslist instance = entityManager.find(Symptomslist.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
