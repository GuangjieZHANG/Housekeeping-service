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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Ordered entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ordered", catalog = "housework")
public class Ordered implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2203566843308309431L;
	private String orderId;
	private Worker worker;
	private User user;
	private Servicetype servicetype;
	private String orderName;
	private String address;
	private Double longitude;
	private Double latitude;
	private Timestamp predictTime;
	private Timestamp startTime;
	private Timestamp finishTime;
	private Boolean isPaid;
	private Double cost;
	private Timestamp addTime;
	private Timestamp modifyTime;
	private Set<Remark> remarks = new HashSet<Remark>(0);

	// Constructors

	/** default constructor */
	public Ordered() {
	}

	/** minimal constructor */
	public Ordered(Worker worker, User user, Servicetype servicetype,
			String address, Double longitude, Double latitude,
			Timestamp predictTime, Timestamp startTime, Timestamp finishTime,
			Boolean isPaid, Double cost, Timestamp modifyTime) {
		this.worker = worker;
		this.user = user;
		this.servicetype = servicetype;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.predictTime = predictTime;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.isPaid = isPaid;
		this.cost = cost;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Ordered(Worker worker, User user, Servicetype servicetype,
			String orderName, String address, Double longitude,
			Double latitude, Timestamp predictTime, Timestamp startTime,
			Timestamp finishTime, Boolean isPaid, Double cost,
			Timestamp addTime, Timestamp modifyTime, Set<Remark> remarks) {
		this.worker = worker;
		this.user = user;
		this.servicetype = servicetype;
		this.orderName = orderName;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.predictTime = predictTime;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.isPaid = isPaid;
		this.cost = cost;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
		this.remarks = remarks;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "orderId", unique = true, nullable = false, length = 32)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workerId", nullable = false)
	public Worker getWorker() {
		return this.worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceTypeId", nullable = false)
	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}

	@Column(name = "orderName", length = 65535)
	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	@Column(name = "address", nullable = false, length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "longitude", nullable = false, precision = 10, scale = 6)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", nullable = false, precision = 10, scale = 6)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "predictTime", nullable = false, length = 19)
	public Timestamp getPredictTime() {
		return this.predictTime;
	}

	public void setPredictTime(Timestamp predictTime) {
		this.predictTime = predictTime;
	}

	@Column(name = "startTime", nullable = false, length = 19)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "finishTime", nullable = false, length = 19)
	public Timestamp getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "isPaid", nullable = false)
	public Boolean getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	@Column(name = "cost", nullable = false, precision = 22)
	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordered")
	public Set<Remark> getRemarks() {
		return this.remarks;
	}

	public void setRemarks(Set<Remark> remarks) {
		this.remarks = remarks;
	}

}