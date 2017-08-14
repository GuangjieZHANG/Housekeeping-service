package entity;

import java.sql.Timestamp;


/**
 * Removeorder entity. @author MyEclipse Persistence Tools
 */

public class Removeorder implements java.io.Serializable {

	// Fields

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
	private Timestamp removeTime;
	private Timestamp addTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public Removeorder() {
	}

public Removeorder( Servicetype servicetype,Timestamp predictTime,User user,Timestamp removeTime,Timestamp startTime) {
	this.servicetype = servicetype;
		this.predictTime = predictTime;
	this.user = user;
	this.removeTime = removeTime;
	this.startTime = startTime;

	}


	/** minimal constructor */
	public Removeorder(User user, Servicetype servicetype, String address,
			Double longitude, Double latitude, Timestamp predictTime,
			Timestamp removeTime, Timestamp modifyTime) {
		this.user = user;
		this.servicetype = servicetype;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.predictTime = predictTime;
		this.removeTime = removeTime;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Removeorder(Worker worker, User user, Servicetype servicetype,
			String orderName, String address, Double longitude,
			Double latitude, Timestamp predictTime, Timestamp startTime,
			Timestamp removeTime, Timestamp addTime, Timestamp modifyTime) {
		this.worker = worker;
		this.user = user;
		this.servicetype = servicetype;
		this.orderName = orderName;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.predictTime = predictTime;
		this.startTime = startTime;
		this.removeTime = removeTime;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public Worker getWorker() {
		return this.worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}


	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}


	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Timestamp getPredictTime() {
		return this.predictTime;
	}

	public void setPredictTime(Timestamp predictTime) {
		this.predictTime = predictTime;
	}


	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}


	public Timestamp getRemoveTime() {
		return this.removeTime;
	}

	public void setRemoveTime(Timestamp removeTime) {
		this.removeTime = removeTime;
	}


	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}


	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

}