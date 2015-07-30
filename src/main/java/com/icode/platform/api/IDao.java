package com.icode.platform.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;


//import org.hibernate.envers.query.AuditQuery;
import org.springframework.dao.DataAccessException;

import com.icode.platform.vo.SearchVo;

//import com.lqdt.platform.vo.SearchVo;

@SuppressWarnings({"hiding", "rawtypes"})
public interface IDao<E extends IEntity, K extends Serializable> {
	
	<E extends IEntity> List<E> runNativeSQL(Class<E> inElementClass, String queryName);
	
	List executeNativeQuery(String query, HashMap<String, Object> criteria);
	
	public void purge(E inEntity) ;

	<E extends IEntity> Long saveOrUpdate(E inEntity) throws DataAccessException;

	void delete(E inEntity);

	<E extends IEntity> E find(Class<E> inEntityClass, K inPkey);

	<E extends IEntity> List<E> findAll(Class<E> inEntityClass,
			Boolean deleted, String orderby,Integer pageSize, Integer pageNo);

	<E extends IEntity> List<E> findAll(Class<E> inEntityClass, Boolean deleted,Integer pageSize, Integer pageNo);

	Integer executeQuery(String queryName, Hashtable<String, Object> criteria);
	
//	<E extends IEntity> AuditQuery getAuditQuery(Class<E> inEntityClass);
	
	<E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, String queryName, HashMap<String, obj> criteria);
    
	<E extends IEntity, obj extends Object> E getEntity(Class<E> inElementClass, String queryName, HashMap<String, obj> criteria);

	<E extends IEntity> List<E> findAll(Class<E> inEntityClass, Boolean deleted, String orderby);
	
	<E extends IEntity> List<E> findAll(Class<E> inEntityClass, Boolean deleted);
	
	<E extends IEntity> List<E> searchTerm(SearchVo searchVo,Class<E> inEntityClass);
	
	<E extends IEntity> List<E> searchByParameters(SearchVo searchVo,Class<E> inEntityClass);
	
	<E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, String queryName, HashMap<String, obj> criteria,Integer pageNumber, Integer pageSize);
	
	<E extends IEntity,O extends Object> List<O> getColumnValues(Class<E> inElementClass, String queryName, HashMap<String,O> criteria);
}