package com.icode.platform.vo;

import java.util.Date;


public class BaseVO {

	private Long pkey;
	
	private Boolean deletedFlag;
	
	private String createdBy;
	
	private Date createdTime;
	
	private String changedBy;
	
	private Date changedTime;
	
	private Integer version;

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
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public Date getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Date changedTime) {
		this.changedTime = changedTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}