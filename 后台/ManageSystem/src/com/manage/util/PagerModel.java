package com.manage.util;

import java.util.List;


//实现分页查询
public class PagerModel {
	// 当前页数
	private int pageNum = 1;
	// 每页显示数量
	private int numPerPage = 20;
	// 总页数
	private int totalPage;
	// 总数量
	private int totalCount;
	// Hql语句
	private String hql;
	// 查询的数据
	@SuppressWarnings("rawtypes")
	private List list;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	// 设置总数量的同时，设置总页数
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		int temp = 0;
		if (totalCount % this.numPerPage != 0) {
			temp++;
		}
		this.totalPage = totalCount / this.numPerPage + temp;
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

}
