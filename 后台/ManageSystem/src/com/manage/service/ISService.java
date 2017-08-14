/**
 * 
 */
package com.manage.service;

import java.util.List;

import com.manage.po.Servicetype;
import com.manage.util.PagerModel;

/**
 * @author 情愿孤独
 *
 */
public interface ISService {
	public PagerModel getSServiceList(int pageno,int pagesize);
	public Servicetype getServicetypeById(String serviceTypeId);
	public List<Servicetype> getServicetypesByIds(String[] serviceIds);
}
