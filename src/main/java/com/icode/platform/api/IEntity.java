package com.icode.platform.api;

import java.util.Calendar;

public interface IEntity {

	public Long getPkey();

	public void setPkey(Long pkey);
	
	public Integer getVersion();

	public void setVersion(Integer version);
	
	public Boolean getDeletedFlag();
    
    public void setDeletedFlag(Boolean deletedFlag);
    
	public String getCreatedBy();

	public void setCreatedBy(String createdBy);
	
	public Calendar getCreatedTime();

	public void setCreatedTime(Calendar createdTime);

	public String getChangedBy();

	public void setChangedBy(String changedBy);

	public Calendar getChangedTime();

	public void setChangedTime(Calendar changedTime);    
}