package com.icode.platform.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

import com.icode.platform.api.IDao;
import com.icode.platform.api.IEntity;
import com.icode.platform.vo.SearchVo;


@SuppressWarnings({"hiding", "unchecked", "rawtypes"})
public class GenericJpaDao<E extends IEntity, K extends Serializable>
		implements IDao<E, K> {

	protected EntityManager entityManager;

	@PersistenceContext(unitName = "punit")
	@Qualifier("entityManagerFactory")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public <E extends IEntity> Long saveOrUpdate(E inEntity) throws DataAccessException {
		
		if (inEntity.getDeletedFlag() == null) {
			inEntity.setDeletedFlag(false);
		}
		
		if (inEntity.getPkey() == null) {
			entityManager.persist(inEntity);
			return (Long) getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(inEntity);
		} else {
			getEntityManager().merge(inEntity);
			return inEntity.getPkey();
		}
	}

	public <E extends IEntity> List<E> findAll(Class<E> inEntityClass, Boolean deletedFlag, String orderby, 
			Integer pageSize, Integer pageNo) {
		
		Entity entity = inEntityClass.getAnnotation(Entity.class);
		
		String entityClsName = inEntityClass.getSimpleName();
		
		if (entity != null) {
			
			String eName = entity.name();
		
			if (eName != null && eName.length() > 0) {
				entityClsName = eName;
			}
		}

		String sQuery = "SELECT instance FROM " + entityClsName + " instance ";
		
		if (deletedFlag != null) {
			if (deletedFlag) {
				sQuery = sQuery + " where instance.deletedFlag IS true";
			} else {
				sQuery = sQuery + " where instance.deletedFlag IS NOT true";
			}
		}
		
		if (orderby == null) {
			sQuery = sQuery + " order by instance.createdTime asc";
		} else {
			sQuery = sQuery + " order by instance." + orderby;
		}

		Query query = getEntityManager().createQuery(sQuery);
		
		if (pageSize != null && pageNo != null) {
			query.setMaxResults(pageSize);
			Integer firstResult = (pageNo - 1) * pageSize;
			query.setFirstResult(firstResult);
		}
		
		return (List<E>) query.getResultList();
	}

	public <E extends IEntity> List<E> findAll(Class<E> inEntityClass, Boolean deletedFlag, Integer pageSize, Integer pageNo) {
		return findAll(inEntityClass, deletedFlag, null, pageSize, pageNo);
	}

	public <E extends IEntity> E find(Class<E> inEntityClass, K pKey) {
		return getEntityManager().find(inEntityClass, pKey);
	}

	@Override
	public void delete(E entity) {
		entity.setDeletedFlag(Boolean.TRUE);
		getEntityManager().merge(entity);
	}

	public void purge(E inEntity) {
		entityManager.remove(inEntity);
	}

	@Override
	public <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, String queryName, HashMap<String, obj> criteria) {
		
		Query qry = entityManager.createNamedQuery(queryName);
		
		Set<String> keys = criteria.keySet();
		
		for (String key : keys) {
			qry.setParameter(key, criteria.get(key));
		}
		
		Object result;
		
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		
		return (List<E>) result;
	}

	@Override
	public <E extends IEntity, obj> E getEntity(Class<E> inElementClass, String queryName, HashMap<String, obj> criteria) {

		Query qry = entityManager.createNamedQuery(queryName);
		
		Set<String> keys = criteria.keySet();
		
		for (String key : keys) {
			qry.setParameter(key, criteria.get(key));
		}
		
		Object result;
		
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		
		if (result != null && ((List) result).size() > 0) {
			return (E) (((List) result).get(0));
		} else {
			return null;
		}
	}

	@Override
	public Integer executeQuery(String queryName, Hashtable<String, Object> criteria) {
		
		Query q = entityManager.createNamedQuery(queryName);
		
		if (criteria != null) {
		
			Enumeration<String> keys = criteria.keys();
			
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				q.setParameter(key, criteria.get(key));
			}
		}
		
		return q.executeUpdate();
	}

	@Override
	public <E extends IEntity> List<E> findAll(Class<E> inEntityClass, Boolean deleted, String orderby) {
		
		Entity entity = inEntityClass.getAnnotation(Entity.class);
		
		String entityClsName = inEntityClass.getSimpleName();
		
		if (entity != null) {
			String eName = entity.name();
			if (eName != null && eName.length() > 0) {
				entityClsName = eName;
			}
		}

		String sQuery = "SELECT instance FROM " + entityClsName + " instance ";
		if (deleted != null) {
			if (deleted) {
				sQuery = sQuery + " where instance.deletedFlag IS true";
			} else {
				sQuery = sQuery + " where instance.deletedFlag IS NOT true";
			}
		}
		
		if (orderby == null) {
			sQuery = sQuery + " order by instance.createdTime asc";
		} else {
			sQuery = sQuery + " order by instance." + orderby;
		}

		Query query = entityManager.createQuery(sQuery);
		
		return (List<E>) query.getResultList();
	}

	@Override
	public <E extends IEntity> List<E> findAll(Class<E> inEntityClass, Boolean deletedFlag) {
		return findAll(inEntityClass, deletedFlag, null);
	}

	@Override
	public <E extends IEntity> List<E> runNativeSQL(Class<E> inElementClass, String queryName) {
		List<E> values = entityManager.createNativeQuery(queryName, inElementClass).getResultList();
		return values;
	}

	@Override
	public List executeNativeQuery(String query, HashMap<String, Object> criteria) {
		
		Query q = entityManager.createNativeQuery(query);
		
		Set<String> keys = criteria.keySet();
		
		for (String key : keys) {
			q.setParameter(key, criteria.get(key));
		}
		
		return q.getResultList();
	}

	/*public <E extends IEntity> AuditQuery getAuditQuery(Class<E> inEntityClass) {
		return AuditReaderFactory.get(entityManager).createQuery().forRevisionsOfEntity(inEntityClass, false, true);
	}*/

	@Override
	public <E extends IEntity> List<E> searchTerm(SearchVo searchVo, Class<E> inEntityClass) {

		String[] fields = searchVo.getFields();
		
		String term = searchVo.getTerm();
		String orderby = searchVo.getOrderby();
		String LOWER_START = " LOWER( ";
		String LOWER_END = " ) ";
		
		Boolean deleted = searchVo.getExclusiveDelete();
		Boolean asc = searchVo.getAsc();
		Boolean sortByName = searchVo.getSortNameByAsc();
		Boolean likeSearch = searchVo.getLikeSearch();
		Boolean caseSensitiveSearch = searchVo.getCaseSensitiveSearch();
		
		if (caseSensitiveSearch) {
			LOWER_START = "";
			LOWER_END = "";
		}

		Entity entity = inEntityClass.getAnnotation(Entity.class);
		String entityClsName = inEntityClass.getSimpleName();
		
		if (entity != null) {
			String eName = entity.name();
			if (eName != null && eName.length() > 0) {
				entityClsName = eName;
			}
		}
		
		List<E> result = new ArrayList<E>();
		
		if (fields != null) {
			for (String field : fields) {
				
				StringBuilder queryBuilder = new StringBuilder();
				
				queryBuilder.append("SELECT instance FROM " + entityClsName + " instance where ");
				queryBuilder.append(LOWER_START + " instance." + field + LOWER_END);
				
				if (likeSearch) {
					queryBuilder.append(" LIKE " + LOWER_START + "'%" + term + "%'" + LOWER_END);
				} else {
					queryBuilder.append(" = " + LOWER_START + "'" + term + "'" + LOWER_END);
				}

				if (deleted != null) {
					if (deleted) {
						queryBuilder.append(" and instance.deletedFlag IS true");
					} else {
						queryBuilder.append(" and instance.deletedFlag IS NOT true");
					}
				}

				if (orderby != null && !"".equalsIgnoreCase(orderby)) {
					if (asc) {
						queryBuilder.append(" order by instance." + orderby + " asc");
					} else {
						queryBuilder.append(" order by instance." + orderby + " desc");
					}
				} else if (sortByName != null && sortByName) {
					queryBuilder.append(" order by instance.name asc");
				} else {
					queryBuilder.append(" order by instance.createdTime desc");
				}

				Query query = entityManager.createQuery(queryBuilder.toString());

				if (searchVo.getPageSize() != null 	&& searchVo.getPageNo() != null) {
					query.setMaxResults(searchVo.getPageSize());
					Integer firstResult = (searchVo.getPageNo() - 1) * searchVo.getPageSize();
					query.setFirstResult(firstResult);
				}

				List<E> resultList = query.getResultList();
				
				if (resultList != null && !resultList.isEmpty()) {
					for (E resultEntity : resultList) {
						if (!result.contains(resultEntity)) { 
							result.add(resultEntity);
						}
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public <E extends IEntity> List<E> searchByParameters(SearchVo searchVo, Class<E> inEntityClass) {

		StringBuilder queryBuilder = new StringBuilder();

		String orderby = searchVo.getOrderby();
		String LOWER_START = " LOWER( ";
		String LOWER_END = " ) ";
		
		Boolean deleted = searchVo.getExclusiveDelete();
		Boolean asc = searchVo.getAsc();
		Boolean sortByName = searchVo.getSortNameByAsc();
		Boolean likeSearch = searchVo.getLikeSearch();
		Boolean caseSensitiveSearch = searchVo.getCaseSensitiveSearch();
		
		if (caseSensitiveSearch) {
			LOWER_START = "";
			LOWER_END = "";
		}

		Entity entity = inEntityClass.getAnnotation(Entity.class);
		String entityClsName = inEntityClass.getSimpleName();
		
		if (entity != null) {
			String eName = entity.name();
			if (eName != null && eName.length() > 0) {
				entityClsName = eName;
			}
		}

		Map<String, String> searchParamMap = searchVo.getSearchParamMap();
		if (searchParamMap != null && searchParamMap.size() > 0) {
			queryBuilder.append("SELECT instance FROM " + entityClsName + " instance where ");
			
			Set<String> keys = searchParamMap.keySet();

			if (keys != null && keys.size() > 0) {
				int counter = 0;
				Iterator<String> keyIterator = keys.iterator();
				while (keyIterator.hasNext()) {
					String columnName = keyIterator.next();
					String columnValue = searchParamMap.get(columnName);
					if (columnName != null && !"".equalsIgnoreCase(columnName) && columnValue != null && !"".equalsIgnoreCase(columnValue)) {
						if (counter > 0) { 
							queryBuilder.append(" AND ");
						}
						queryBuilder.append(LOWER_START + " instance." + columnName + LOWER_END);
						if (likeSearch) {
							queryBuilder.append(" LIKE " + LOWER_START + "'%" + columnValue + "%'" + LOWER_END);
						} else {
							queryBuilder.append(" = " + LOWER_START + "'" + columnValue + "'" + LOWER_END);
						}
					}
					counter++;
				}

				if (counter > 0) {
					if (deleted != null) {
						if (deleted) {
							queryBuilder.append(" and instance.deletedFlag IS true");
						} else {
							queryBuilder.append(" and instance.deletedFlag IS NOT true");
						}
					}

					if (orderby != null && !"".equalsIgnoreCase(orderby)) {
						if (asc)
							queryBuilder.append(" order by instance." + orderby + " asc");
						else
							queryBuilder.append(" order by instance." + orderby + " desc");
					} else if (sortByName != null && sortByName) {
						queryBuilder.append(" order by instance.name asc");
					} else {
						queryBuilder.append(" order by instance.createdTime desc");
					}
				}
			}
		}

		if (queryBuilder != null && queryBuilder.toString() != null && queryBuilder.toString().length() > 0) {

			Query query = entityManager.createQuery(queryBuilder.toString());

			List<E> result = new ArrayList<E>();

			if (searchVo.getPageSize() != null && searchVo.getPageNo() != null) {
				query.setMaxResults(searchVo.getPageSize());
				Integer firstResult = (searchVo.getPageNo() - 1) * searchVo.getPageSize();
				query.setFirstResult(firstResult);
			}

			List<E> resultList = query.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				for (E resultEntity : resultList) {
					if (!result.contains(resultEntity)) { 
						result.add(resultEntity);
					}
				}
				
				return result;
			}
		}

		return null;
	}

	@Override
	public <E extends IEntity, obj> List<E> getEntities(Class<E> inElementClass, String queryName, HashMap<String, obj> criteria, Integer pageNumber, Integer pageSize) {
		
		Query qry = entityManager.createNamedQuery(queryName);
		
		Set<String> keys = criteria.keySet();
		
		for (String key : keys) {
			qry.setParameter(key, criteria.get(key));
		}
		
		if (pageSize != null && pageNumber != null) {
			qry.setMaxResults(pageSize);
			Integer firstResult = (pageNumber - 1) * pageSize;
			qry.setFirstResult(firstResult);
		}
		
		Object result;
		
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		
		return (List<E>) result;
	}

	@Override
	public <E extends IEntity, O extends Object> List<O> getColumnValues(Class<E> inElementClass, String queryName,	HashMap<String, O> criteria) {
		
		Query qry = entityManager.createNamedQuery(queryName);
		
		Set<String> keys = criteria.keySet();
		
		for (String key : keys) {
			qry.setParameter(key, criteria.get(key));
		}
		
		Object result = qry.getResultList();
		
		return (List<O>) result;
	}
}