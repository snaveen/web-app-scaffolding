package com.icode.platform.dto;

import java.util.Calendar;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class AbstractDTO {

	protected Long pkey;
	
	protected Boolean deletedFlag = false;
	
	protected String createdBy;
	
	protected Calendar createdTime;
	
	protected String changedBy;
	
	protected Calendar changedTime;
	
	protected Integer version;
	
	public Long getPkey() {
		return pkey;
	}

	public void setPkey(Long pkey) {
		this.pkey = pkey;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if (createdBy != null)
			this.createdBy = createdBy;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		if (createdTime != null)
			this.createdTime = createdTime;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		if (changedBy != null)
			this.changedBy = changedBy;
	}

	public Calendar getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Calendar changedTime) {
		if (changedTime != null)
			this.changedTime = changedTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}