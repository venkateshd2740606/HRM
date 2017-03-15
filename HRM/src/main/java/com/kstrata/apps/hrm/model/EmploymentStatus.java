package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employmentstatus database table.
 * 
 */
@Entity
@Table(name="employmentstatus")
@NamedQuery(name="Employmentstatus.findAll", query="SELECT e FROM EmploymentStatus e")
public class EmploymentStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 78792458835203410L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=400)
	private String description;

	@Column(length=100)
	private String name;

	public EmploymentStatus() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}