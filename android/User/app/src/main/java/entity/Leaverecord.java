package entity;

import java.sql.Timestamp;


/**
 * Leaverecord entity. @author MyEclipse Persistence Tools
 */
public class Leaverecord implements java.io.Serializable {

	// Fields

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

	public String getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}


	public Worker getWorker() {
		return this.worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}


	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}


	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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