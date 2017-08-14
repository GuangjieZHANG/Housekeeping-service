package entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String phoneNumber;
	private String password;
	private String address;
	private String photo;
	private Double longtitude;
	private Double latitude;
	private Timestamp addTime;
	private Timestamp modifyTime;
	private Set<Ordered> ordereds = new HashSet<Ordered>(0);
	private Set<Unorder> unorders = new HashSet<Unorder>(0);
	private Set<Ordering> orderings = new HashSet<Ordering>(0);
	private Set<Removeorder> removeorders = new HashSet<Removeorder>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String phoneNumber, String password,
			Timestamp modifyTime) {
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public User(String userName, String phoneNumber, String password,
			String address, String photo, Double longtitude, Double latitude,
			Timestamp addTime, Timestamp modifyTime, Set<Ordered> ordereds,
			Set<Unorder> unorders, Set<Ordering> orderings,
			Set<Removeorder> removeorders) {
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
		this.photo = photo;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
		this.ordereds = ordereds;
		this.unorders = unorders;
		this.orderings = orderings;
		this.removeorders = removeorders;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}


	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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


	public Set<Ordered> getOrdereds() {
		return this.ordereds;
	}

	public void setOrdereds(Set<Ordered> ordereds) {
		this.ordereds = ordereds;
	}


	public Set<Unorder> getUnorders() {
		return this.unorders;
	}

	public void setUnorders(Set<Unorder> unorders) {
		this.unorders = unorders;
	}


	public Set<Ordering> getOrderings() {
		return this.orderings;
	}

	public void setOrderings(Set<Ordering> orderings) {
		this.orderings = orderings;
	}


	public Set<Removeorder> getRemoveorders() {
		return this.removeorders;
	}

	public void setRemoveorders(Set<Removeorder> removeorders) {
		this.removeorders = removeorders;
	}

}