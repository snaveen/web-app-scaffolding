package com.icode.platform.vo;

import java.util.Map;

public class SearchVo extends BaseVO {
	
	private String term;
	
	private String[] fields;
	
	private Boolean deleted;
	
	private String orderby;

	private Boolean caseSensitiveSearch;
	
	private Boolean likeSearch;
	
	private Boolean sortNameByAsc;
	
	private Integer pageSize;
	
	private Integer pageNo;
	
	private Map<String, String> searchParamMap;
	
	private Boolean asc;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public Boolean getDeleted() {
		if (deleted == null) {
			deleted = false;
		}
		return deleted;
	}

	public Boolean getExclusiveDelete() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Boolean getCaseSensitiveSearch() {
		if (caseSensitiveSearch == null) {
			caseSensitiveSearch = false;
		}
		return caseSensitiveSearch;
	}

	public void setCaseSensitiveSearch(Boolean caseSensitiveSearch) {
		this.caseSensitiveSearch = caseSensitiveSearch;
	}

	public Boolean getLikeSearch() {
		if (likeSearch == null) {
			likeSearch = true;
		}
		return likeSearch;
	}

	public void setLikeSearch(Boolean likeSearch) {
		this.likeSearch = likeSearch;
	}

	public Boolean getSortNameByAsc() {
		return sortNameByAsc;
	}

	public void setSortNameByAsc(Boolean sortNameByAsc) {
		this.sortNameByAsc = sortNameByAsc;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Map<String, String> getSearchParamMap() {
		return searchParamMap;
	}

	public void setSearchParamMap(Map<String, String> searchParamMap) {
		this.searchParamMap = searchParamMap;
	}

	public Boolean getAsc() {
		if (asc == null)
			asc = true;
		return asc;
	}

	public void setAsc(Boolean asc) {
		this.asc = asc;
	}
}