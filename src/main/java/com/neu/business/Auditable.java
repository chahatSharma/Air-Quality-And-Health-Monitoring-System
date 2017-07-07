package com.neu.business;

import java.util.Date;

public interface Auditable {
	public void setModifiedTime(Date date);
	public void setCreationTime(Date date);

}
