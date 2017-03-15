package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the holidays database table.
 * 
 */
@Entity
@Table(name="holidays")
@NamedQueries({
	@NamedQuery(name="Holiday.findAll", query="SELECT h FROM Holiday h order by h.date desc"),
	@NamedQuery(name="Holiday.findById", query="SELECT h FROM Holiday h where h.id = :id"),
	@NamedQuery(name="Holiday.validateDate", query="SELECT h FROM Holiday h where h.date = :date"),
	@NamedQuery(name="Holiday.validateName", query="SELECT h FROM Holiday h where h.name= :name")
})


public class Holiday implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1245715302087796423L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(nullable=false)
	private String name;

	private String status;
	
	@Transient
	private boolean isEditable;

	public Holiday() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

}