package com.manage.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menu", catalog = "housework")
public class Menu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1417274390244382808L;
	private Short menuId;
	private Short isLeaf;
	private String name;
	private String pageno;
	private Short parentno;
	private String url;
	private Set<Role> roles = new HashSet<Role>(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Short menuId) {
		this.menuId = menuId;
	}

	/** full constructor */
	public Menu(Short menuId, Short isLeaf, String name, String pageno,
			Short parentno, String url, Set<Role> roles) {
		this.menuId = menuId;
		this.isLeaf = isLeaf;
		this.name = name;
		this.pageno = pageno;
		this.parentno = parentno;
		this.url = url;
		this.roles = roles;
	}

	// Property accessors
	@Id
	@Column(name = "menuId", unique = true, nullable = false)
	public Short getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Short menuId) {
		this.menuId = menuId;
	}

	@Column(name = "isLeaf")
	public Short getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Short isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "name", length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pageno", length = 65535)
	public String getPageno() {
		return this.pageno;
	}

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

	@Column(name = "parentno")
	public Short getParentno() {
		return this.parentno;
	}

	public void setParentno(Short parentno) {
		this.parentno = parentno;
	}

	@Column(name = "url", length = 65535)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menus")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}