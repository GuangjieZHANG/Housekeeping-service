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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.JsonAdapter;
import com.manage.adapter.WorkerAdapter;

/**
 * Worker entity. @author MyEclipse Persistence Tools
 */
@JsonAdapter(WorkerAdapter.class)
@Entity
@Table(name = "worker", catalog = "housework")
public class Worker implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7345020270778332002L;
	private String workerId;
	private String number;
	private String workerName;
	private String password;
	private String phoneNumber;
	private String cardId;
	private Short age;
	private String sex;
	private String brief;
	private String address;
	private String photo;
	private Double longitude;
	private Double latitude;
	private Timestamp addtime;
	private Timestamp modifyTime;
	private Set<Removeorder> removeorders = new HashSet<Removeorder>(0);
	private Set<Leaverecord> leaverecords = new HashSet<Leaverecord>(0);
	private Set<Unorder> unorders = new HashSet<Unorder>(0);
	private Set<Servicetype> servicetypes = new HashSet<Servicetype>(0);
	private Set<Ordering> orderings = new HashSet<Ordering>(0);
	private Set<Ordered> ordereds = new HashSet<Ordered>(0);

	// Constructors

	/** default constructor */
	public Worker() {
	}

	/** minimal constructor */
	public Worker(String workerName, String password, String phoneNumber,
			String cardId, Short age, String sex, String brief,
			Timestamp modifyTime) {
		this.workerName = workerName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.cardId = cardId;
		this.age = age;
		this.sex = sex;
		this.brief = brief;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Worker(String number, String workerName, String password,
			String phoneNumber, String cardId, Short age, String sex,
			String brief, String address, String photo, Double longitude,
			Double latitude, Timestamp addtime, Timestamp modifyTime,
			Set<Removeorder> removeorders, Set<Leaverecord> leaverecords,
			Set<Unorder> unorders, Set<Servicetype> servicetypes,
			Set<Ordering> orderings, Set<Ordered> ordereds) {
		this.number = number;
		this.workerName = workerName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.cardId = cardId;
		this.age = age;
		this.sex = sex;
		this.brief = brief;
		this.address = address;
		this.photo = photo;
		this.longitude = longitude;
		this.latitude = latitude;
		this.addtime = addtime;
		this.modifyTime = modifyTime;
		this.removeorders = removeorders;
		this.leaverecords = leaverecords;
		this.unorders = unorders;
		this.servicetypes = servicetypes;
		this.orderings = orderings;
		this.ordereds = ordereds;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "workerId", unique = true, nullable = false, length = 32)
	public String getWorkerId() {
		return this.workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	@Column(name = "number", length = 10)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "workerName", nullable = false, length = 65535)
	public String getWorkerName() {
		return this.workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	@Column(name = "password", nullable = false, length = 20, updatable=false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phoneNumber", nullable = false, length = 11)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "cardID", nullable = false, length = 18)
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Column(name = "age", nullable = false)
	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	@Column(name = "sex", nullable = false, length = 65535)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "brief", nullable = false, length = 65535)
	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "longitude", precision = 10, scale = 6)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", precision = 10, scale = 6)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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

	@OrderBy(value="removeTime desc")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
	public Set<Removeorder> getRemoveorders() {
		return this.removeorders;
	}

	public void setRemoveorders(Set<Removeorder> removeorders) {
		this.removeorders = removeorders;
	}

	@OrderBy(value="addTime desc")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
	public Set<Leaverecord> getLeaverecords() {
		return this.leaverecords;
	}

	public void setLeaverecords(Set<Leaverecord> leaverecords) {
		this.leaverecords = leaverecords;
	}

	@OrderBy(value="addTime desc")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
	public Set<Unorder> getUnorders() {
		return this.unorders;
	}

	public void setUnorders(Set<Unorder> unorders) {
		this.unorders = unorders;
	}

	@OrderBy(value="addtime desc")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "workerservice", catalog = "housework", joinColumns = { @JoinColumn(name = "workerId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "serviceTypeId", nullable = false, updatable = false) })
	public Set<Servicetype> getServicetypes() {
		return this.servicetypes;
	}

	public void setServicetypes(Set<Servicetype> servicetypes) {
		this.servicetypes = servicetypes;
	}

	@OrderBy(value="startTime desc")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
	public Set<Ordering> getOrderings() {
		return this.orderings;
	}

	public void setOrderings(Set<Ordering> orderings) {
		this.orderings = orderings;
	}

	@OrderBy(value="finishTime desc")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
	public Set<Ordered> getOrdereds() {
		return this.ordereds;
	}

	public void setOrdereds(Set<Ordered> ordereds) {
		this.ordereds = ordereds;
	}

}