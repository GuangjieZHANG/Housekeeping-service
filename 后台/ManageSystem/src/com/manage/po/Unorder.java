package com.manage.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Unorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "unorder", catalog = "housework")
public class Unorder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3817709916987028431L;
	private String orderId;
	private Worker worker;
	private User user;
	private Servicetype servicetype;
	private String orderName;
	private String address;
	private Double longitude;
	private Double latitude;
	private Boolean isReced;
	private Timestamp predictTime;
	private Timestamp timer;
	private Timestamp addTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public Unorder() {
	}

	/** minimal constructor */
	public Unorder(User user, Servicetype servicetype, String address,
			Double longitude, Double latitude, Boolean isReced,
			Timestamp predictTime, Timestamp timer, Timestamp modifyTime) {
		this.user = user;
		this.servicetype = servicetype;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.isReced = isReced;
		this.predictTime = predictTime;
		this.timer = timer;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Unorder(Worker worker, User user, Servicetype servicetype,
			String orderName, String address, Double longitude,
			Double latitude, Boolean isReced, Timestamp predictTime,
			Timestamp timer, Timestamp addTime, Timestamp modifyTime) {
		this.worker = worker;
		this.user = user;
		this.servicetype = servicetype;
		this.orderName = orderName;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.isReced = isReced;
		this.predictTime = predictTime;
		this.timer = timer;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
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
	@JoinColumn(name = "workerId")
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

	@Column(name = "isReced", nullable = false)
	public Boolean getIsReced() {
		return this.isReced;
	}

	public void setIsReced(Boolean isReced) {
		this.isReced = isReced;
	}

	@Column(name = "predictTime", nullable = false, length = 19)
	public Timestamp getPredictTime() {
		return this.predictTime;
	}

	public void setPredictTime(Timestamp predictTime) {
		this.predictTime = predictTime;
	}

	@Column(name = "timer", nullable = false, length = 19)
	public Timestamp getTimer() {
		return this.timer;
	}

	public void setTimer(Timestamp timer) {
		this.timer = timer;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Unorder [orderId=" + orderId + ", worker=" + worker.getWorkerId() + ", user="
				+ user.getUserId() + ", servicetype=" + servicetype.getServiceTypeId() + ", orderName="
				+ orderName + ", address=" + address + ", longitude="
				+ longitude + ", latitude=" + latitude + ", isReced=" + isReced
				+ ", predictTime=" + predictTime + ", timer=" + timer
				+ ", addTime=" + addTime + ", modifyTime=" + modifyTime + "]";
	}

}