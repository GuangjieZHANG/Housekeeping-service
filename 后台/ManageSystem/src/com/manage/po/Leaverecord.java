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
 * Leaverecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "leaverecord", catalog = "housework")
public class Leaverecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4813436109193710963L;
	private String leaveId;
	private Worker worker;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String description;
	private Timestamp addTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public Leaverecord() {
	}

	/** minimal constructor */
	public Leaverecord(Worker worker, Timestamp beginTime, Timestamp endTime,
			String description, Timestamp modifyTime) {
		this.worker = worker;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.description = description;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Leaverecord(Worker worker, Timestamp beginTime, Timestamp endTime,
			String description, Timestamp addTime, Timestamp modifyTime) {
		this.worker = worker;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.description = description;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "leaveId", unique = true, nullable = false, length = 32)
	public String getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workerId", nullable = false)
	public Worker getWorker() {
		return this.worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Column(name = "beginTime", nullable = false, length = 19)
	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "endTime", nullable = false, length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}