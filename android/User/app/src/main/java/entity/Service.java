package entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Service entity. @author MyEclipse Persistence Tools
 */

public class Service implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serviceId;
	private String serviceName;
	private String description;
	private Double userPrice;
	private Double workerPrice;
	private Timestamp addtime;
	private Timestamp modifyTime;
	private Set<Removeorder> removeorders = new HashSet<Removeorder>(0);
	private Set<Ordered> ordereds = new HashSet<Ordered>(0);
	private Set<Ordering> orderings = new HashSet<Ordering>(0);
	private Set<Unorder> unorders = new HashSet<Unorder>(0);

	// Constructors

	/** default constructor */
	public Service() {
	}

	/** minimal constructor */
	public Service(String serviceName, String description, Double userPrice,
			Double workerPrice) {
		this.serviceName = serviceName;
		this.description = description;
		this.userPrice = userPrice;
		this.workerPrice = workerPrice;
	}

	/** full constructor */
	public Service(String serviceName, String description, Double userPrice,
			Double workerPrice, Timestamp addtime, Timestamp modifyTime,
			Set<Removeorder> removeorders, Set<Ordered> ordereds, Set<Ordering> orderings, Set<Unorder> unorders) {
		this.serviceName = serviceName;
		this.description = description;
		this.userPrice = userPrice;
		this.workerPrice = workerPrice;
		this.addtime = addtime;
		this.modifyTime = modifyTime;
		this.removeorders = removeorders;
		this.ordereds = ordereds;
		this.orderings = orderings;
		this.unorders = unorders;
	}

	// Property accessors

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUserPrice() {
		return this.userPrice;
	}

	public void setUserPrice(Double userPrice) {
		this.userPrice = userPrice;
	}

	public Double getWorkerPrice() {
		return this.workerPrice;
	}

	public void setWorkerPrice(Double workerPrice) {
		this.workerPrice = workerPrice;
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

	public Set<Removeorder> getRemoveorders() {
		return this.removeorders;
	}

	public void setRemoveorders(Set<Removeorder> removeorders) {
		this.removeorders = removeorders;
	}

	public Set<Ordered> getOrdereds() {
		return this.ordereds;
	}

	public void setOrdereds(Set<Ordered> ordereds) {
		this.ordereds = ordereds;
	}

	public Set<Ordering> getOrderings() {
		return this.orderings;
	}

	public void setOrderings(Set<Ordering> orderings) {
		this.orderings = orderings;
	}

	public Set<Unorder> getUnorders() {
		return this.unorders;
	}

	public void setUnorders(Set<Unorder> unorders) {
		this.unorders = unorders;
	}

}