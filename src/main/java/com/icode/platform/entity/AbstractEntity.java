package com.icode.platform.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.icode.platform.api.IEntity;


@MappedSuperclass
@EntityListeners(value = { PersistenceListener.class })
public class AbstractEntity implements IEntity, Serializable {

	private static final long serialVersionUID = 7665388940974264209L;

	protected Long pkey;
	
	protected Boolean deletedFlag = false;
	
	protected String createdBy;
	
	protected Calendar createdTime;
	
	protected String changedBy;
	
	protected Calendar changedTime;
	
	protected Integer version;

	@Id
	@GeneratedValue
	@Column(name = "PKEY")
	public Long getPkey() {
		return pkey;
	}

	public void setPkey(Long pkey) {
		this.pkey = pkey;
	}

	@Column(name = "DELETED_FLAG")
	@NotNull
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	@Column(name = "CREATED_BY")
//	@NotNull
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if (createdBy != null)
			this.createdBy = createdBy;
	}

	@Column(name = "CREATED_TS")
	@NotNull
	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		if (createdTime != null)
			this.createdTime = createdTime;
	}

	@Column(name = "CHANGED_BY")
//	@NotNull
	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		if (changedBy != null)
			this.changedBy = changedBy;
	}

	@Column(name = "CHANGED_TS")
	@NotNull
	public Calendar getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Calendar changedTime) {
		if (changedTime != null)
			this.changedTime = changedTime;
	}

	@Version
	@Column(name = "VERSION")
	@NotNull
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof IEntity && pkey != null) {
			return pkey.equals(((IEntity) o).getPkey());
		} else { 
			return false;
		}
	}
}
