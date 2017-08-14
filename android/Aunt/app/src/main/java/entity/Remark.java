package entity;

import java.sql.Timestamp;


/**
 * Remark entity. @author MyEclipse Persistence Tools
 */

public class Remark implements java.io.Serializable {

	// Fields

	private String remarkId;
	private Ordered ordered;
	private Short level;
	private String content;
	private String sendId;
	private String receId;
	private Timestamp addTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public Remark() {
	}

	/** minimal constructor */
	public Remark(Ordered ordered, Short level, String content, String sendId,
			String receId, Timestamp modifyTime) {
		this.ordered = ordered;
		this.level = level;
		this.content = content;
		this.sendId = sendId;
		this.receId = receId;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Remark(Ordered ordered, Short level, String content, String sendId,
			String receId, Timestamp addTime, Timestamp modifyTime) {
		this.ordered = ordered;
		this.level = level;
		this.content = content;
		this.sendId = sendId;
		this.receId = receId;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public String getRemarkId() {
		return this.remarkId;
	}

	public void setRemarkId(String remarkId) {
		this.remarkId = remarkId;
	}


	public Ordered getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Ordered ordered) {
		this.ordered = ordered;
	}


	public Short getLevel() {
		return this.level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}


	public String getReceId() {
		return this.receId;
	}

	public void setReceId(String receId) {
		this.receId = receId;
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