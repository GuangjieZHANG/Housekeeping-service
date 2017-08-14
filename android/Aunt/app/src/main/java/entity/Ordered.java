package entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Ordered entity. @author MyEclipse Persistence Tools
 */

public class Ordered implements java.io.Serializable {

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

		public Ordered(Servicetype servicetype,Timestamp finishTime,Worker worker, User user,Double cost){
			this.servicetype = servicetype;
		this.finishTime = finishTime;
			this.worker = worker;
			this.user = user;
			this.cost = cost;
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


	public Timestamp getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}


	public Boolean getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}


	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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


	public Set<Remark> getRemarks() {
		return this.remarks;
	}

	public void setRemarks(Set<Remark> remarks) {
		this.remarks = remarks;
	}

}