package com.manage.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account", catalog = "housework")
public class Account implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2011330511636280132L;
	private String accountId;
	private Role role;
	private String name;
	private String password;
	private Timestamp addTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String accountId, Role role, String name, String password,
			Timestamp modifyTime) {
		this.accountId = accountId;
		this.role = role;
		this.name = name;
		this.password = password;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Account(String accountId, Role role, String name, String password,
			Timestamp addTime, Timestamp modifyTime) {
		this.accountId = accountId;
		this.role = role;
		this.name = name;
		this.password = password;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors
	@Id
	@Column(name = "accountId", unique = true, nullable = false, length = 10)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "name", nullable = false, length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", nullable = false, length = 20, updatable=false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "addTime", length = 19)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(name = "modifyTime", nullable = false, length = 19, insertable=false, updatable=false)
	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

}