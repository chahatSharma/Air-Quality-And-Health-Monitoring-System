package com.neu.business;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


public class AuditableInterceptor extends EmptyInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1579830185569911338L;
	private static final Logger logger = LoggerFactory.getLogger(AuditableInterceptor.class);

	Session session;
	public void setSession(Session session) {
		this.session=session;
	}
	@Override
	public Object instantiate(String entityName, EntityMode entityMode,
	        Serializable id) {
	    logger.info("instantiate: Instantiating object " + entityName + 
	        " with id - " + id + " in mode " + entityMode);
	        return null;
	}
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		
		if(entity instanceof Auditable)
		{logger.info("inside onflushdirty" + entity);
			for(int i=0;i< propertyNames.length;i++)
			{logger.info("inside onflushdirty" + propertyNames[i]);
				if("modifiedTime".equalsIgnoreCase(propertyNames[i]))
				{
					currentState[i] = new Date();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		
		if(entity instanceof Auditable)
		{
			int x= 0;
			for(int i=0;i< propertyNames.length;i++)
			{ logger.info("inside onsave " + propertyNames[i]);
				if(("creationTime".equalsIgnoreCase(propertyNames[i])) )
				{
					x=x+1;
					state[i] = new Date();
					logger.info("inside onsave " + state[i]);
					
					
				}
				if( "modifiedTime".equalsIgnoreCase(propertyNames[i]))
						{
					x=x+1;
					state[i] = new Date();
					logger.info("inside onsave " + state[i]);
					
					
						}
			}
			if(x>0)
				return true;
		}
		return false;
	}

	

}
