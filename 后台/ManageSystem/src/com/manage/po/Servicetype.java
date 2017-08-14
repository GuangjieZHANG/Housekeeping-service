package com.manage.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * Servicetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "servicetype", catalog = "housework")
public class Servicetype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8184447820049392065L;
	private String serviceTypeId;
	private String serviceTypeName;
	private String description;
	private Double userPrice;
	private Double workerPrice;
	private Timestamp addtime;
	private Timestamp modifyTime;
	private Set<Removeorder> removeorders = new HashSet<Removeorder>(0);
	private Set<Ordered> ordereds = new HashSet<Ordered>(0);
	private Set<Worker> workers = new HashSet<Worker>(0);
	private Set<Ordering> orderings = new HashSet<Ordering>(0);
	private Set<Unorder> unorders = new HashSet<Unorder>(0);

	// Constructors

	/** default constructor */
	public Servicetype() {
	}

	/** minimal constructor */
	public Servicetype(String serviceTypeName, String description,
			Double userPrice, Double workerPrice, Timestamp modifyTime) {
		this.serviceTypeName = serviceTypeName;
		this.description = description;
		this.userPrice = userPrice;
		this.workerPrice = workerPrice;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Servicetype(String serviceTypeName, String description,
			Double userPrice, Double workerPrice, Timestamp addtime,
			Timestamp modifyTime, Set<Removeorder> removeorders,
			Set<Ordered> ordereds, Set<Worker> workers,
			Set<Ordering> orderings, Set<Unorder> unorders) {
		this.serviceTypeName = serviceTypeName;
		this.description = description;
		this.userPrice = userPrice;
		this.workerPrice = workerPrice;
		this.addtime = addtime;
		this.modifyTime = modifyTime;
		this.removeorders = removeorders;
		this.ordereds = ordereds;
		this.workers = workers;
		this.orderings = orderings;
		this.unorders = unorders;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "serviceTypeId", unique = true, nullable = false, length = 32)
	public String getServiceTypeId() {
		return this.serviceTypeId;
	}

	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	@Column(name = "serviceTypeName", nullable = false, length = 65535)
	public String getServiceTypeName() {
		return this.serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "userPrice", nullable = false, precision = 22)
	public Double getUserPrice() {
		return this.userPrice;
	}

	public void setUserPrice(Double userPrice) {
		this.userPrice = userPrice;
	}

	@Column(name = "workerPrice", nullable = false, precision = 22)
	public Double getWorkerPrice() {
		return this.workerPrice;
	}

	public void setWorkerPrice(Double workerPrice) {
		this.workerPrice = workerPrice;
	}

	@Column(name = "addtime", length = 19)
	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	@Column(name = "modifyTime", nullable = false, length = 19, insertable=false, updatable=false)
	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servicetype")
	public Set<Removeorder> getRemoveorders() {
		return this.removeorders;
	}

	public void setRemoveorders(Set<Removeorder> removeorders) {
		this.removeorders = removeorders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servicetype")
	public Set<Ordered> getOrdereds() {
		return this.ordereds;
	}

	public void setOrdereds(Set<Ordered> ordereds) {
		this.ordereds = ordereds;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servicetypes")
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	public Set<Worker> getWorkers() {
		return this.workers;
	}

	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servicetype")
	public Set<Ordering> getOrderings() {
		return this.orderings;
	}

	public void setOrderings(Set<Ordering> orderings) {
		this.orderings = orderings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servicetype")
	public Set<Unorder> getUnorders() {
		return this.unorders;
	}

	public void setUnorders(Set<Unorder> unorders) {
		this.unorders = unorders;
	}

}