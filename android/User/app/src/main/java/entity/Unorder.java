package entity;

import com.google.gson.annotations.JsonAdapter;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * Unorder entity. @author MyEclipse Persistence Tools
 */

public class Unorder implements Serializable {

	// Fields

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


    public Unorder( User user, Servicetype servicetype,Timestamp predictTime,String address){
		this.user = user;
		this.servicetype = servicetype;
		this.predictTime = predictTime;
		this.address = address;
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


	public Boolean getIsReced() {
		return this.isReced;
	}

	public void setIsReced(Boolean isReced) {
		this.isReced = isReced;
	}


	public Timestamp getPredictTime() {
		return this.predictTime;
	}

	public void setPredictTime(Timestamp predictTime) {
		this.predictTime = predictTime;
	}


	public Timestamp getTimer() {
		return this.timer;
	}

	public void setTimer(Timestamp timer) {
		this.timer = timer;
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