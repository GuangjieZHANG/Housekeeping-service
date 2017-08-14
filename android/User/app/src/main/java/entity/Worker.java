package entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Worker entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name = "worker", catalog = "housework")
public class Worker implements java.io.Serializable {

	// Fields

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
	private Double latitudde;
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

	public Worker(String workerName,String phoneNumber,String photo){
		this.workerName = workerName;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
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
			Double latitudde, Set<Removeorder> removeorders,
			Set<Leaverecord> leaverecords, Set<Unorder> unorders,
			Set<Servicetype> servicetypes, Set<Ordering> orderings,
			Set<Ordered> ordereds) {
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
		this.latitudde = latitudde;
		this.removeorders = removeorders;
		this.leaverecords = leaverecords;
		this.unorders = unorders;
		this.servicetypes = servicetypes;
		this.orderings = orderings;
		this.ordereds = ordereds;
	}

	// Property accessors
	/*@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "workerId", unique = true, nullable = false, length = 32)*/
	public String getWorkerId() {
		return this.workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}


	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	public String getWorkerName() {
		return this.workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}


	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}


	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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


	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}


	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}


	public Double getLatitudde() {
		return this.latitudde;
	}

	public void setLatitudde(Double latitudde) {
		this.latitudde = latitudde;
	}


	public Set<Removeorder> getRemoveorders() {
		return this.removeorders;
	}

	public void setRemoveorders(Set<Removeorder> removeorders) {
		this.removeorders = removeorders;
	}


	public Set<Leaverecord> getLeaverecords() {
		return this.leaverecords;
	}

	public void setLeaverecords(Set<Leaverecord> leaverecords) {
		this.leaverecords = leaverecords;
	}


	public Set<Unorder> getUnorders() {
		return this.unorders;
	}

	public void setUnorders(Set<Unorder> unorders) {
		this.unorders = unorders;
	}

	public Set<Servicetype> getServicetypes() {
		return this.servicetypes;
	}

	public void setServicetypes(Set<Servicetype> servicetypes) {
		this.servicetypes = servicetypes;
	}


	public Set<Ordering> getOrderings() {
		return this.orderings;
	}

	public void setOrderings(Set<Ordering> orderings) {
		this.orderings = orderings;
	}


	public Set<Ordered> getOrdereds() {
		return this.ordereds;
	}

	public void setOrdereds(Set<Ordered> ordereds) {
		this.ordereds = ordereds;
	}

}