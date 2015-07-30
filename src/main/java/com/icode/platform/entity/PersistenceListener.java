package com.icode.platform.entity;

import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.icode.platform.util.Constants;

public class PersistenceListener {
	
	@PrePersist
	void onPreCreate(Object entity) {
		
		AbstractEntity baseEntity = (AbstractEntity) entity;
		
		if (baseEntity.getCreatedTime() == null) {
			baseEntity.setCreatedTime(Calendar.getInstance(TimeZone.getTimeZone(Constants.GMT)));
		}
		
		baseEntity.setChangedTime(Calendar.getInstance(TimeZone.getTimeZone(Constants.GMT)));
		
		if (baseEntity.getCreatedBy() == null || baseEntity.getCreatedBy().length() <= 0) {
			/*String createdBy = UtilityFunctions.getCurrentLoggedinUser();
			baseEntity.setCreatedBy(createdBy);
			baseEntity.setChangedBy(createdBy);*/
		} else{
			baseEntity.setChangedBy(baseEntity.getCreatedBy());
		}
			
		if (baseEntity.getDeletedFlag() == null) {
			baseEntity.setDeletedFlag(false);
		}
	}
	
	@PreUpdate
	void onPreUpdate(Object entity) {
		
		AbstractEntity baseEntity = (AbstractEntity) entity;
		
		baseEntity.setChangedTime(Calendar.getInstance(TimeZone.getTimeZone(Constants.GMT)));
		
//		String changedBy = UtilityFunctions.getCurrentLoggedinUser();
		
//		baseEntity.setChangedBy(changedBy);
		
		if (baseEntity.getDeletedFlag() == null) {
			baseEntity.setDeletedFlag(false);
		}
	}
}