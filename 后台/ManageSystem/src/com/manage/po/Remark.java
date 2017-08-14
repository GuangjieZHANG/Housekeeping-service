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
 * Remark entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "remark", catalog = "housework")
public class Remark implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 792238190534491417L;
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
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "remarkId", unique = true, nullable = false, length = 32)
	public String getRemarkId() {
		return this.remarkId;
	}

	public void setRemarkId(String remarkId) {
		this.remarkId = remarkId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId", nullable = false)
	public Ordered getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Ordered ordered) {
		this.ordered = ordered;
	}

	@Column(name = "level", nullable = false)
	public Short getLevel() {
		return this.level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "sendId", nullable = false, length = 32)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "receId", nullable = false, length = 32)
	public String getReceId() {
		return this.receId;
	}

	public void setReceId(String receId) {
		this.receId = receId;
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