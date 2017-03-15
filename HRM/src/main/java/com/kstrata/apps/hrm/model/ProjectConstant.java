package com.kstrata.apps.hrm.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the project_constants database table.
 * 
 */
@Entity
@Table(name="project_constants")
@NamedQueries({
	@NamedQuery(name = "ProjectConstant.findAll", query = "SELECT p FROM ProjectConstant p"),
	@NamedQuery(name = "ProjectConstant.findByType", query = "SELECT p FROM ProjectConstant p where p.type = :type") 
})
public class ProjectConstant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=300)
	private String description;

	@Column(nullable=false, length=300)
	private String name;

	@Column(name="short_name", length=300)
	private String shortName;

	@Column(nullable=false, length=100)
	private String type;

	//bi-directional many-to-one association to Employeeleave
	@OneToMany(mappedBy="leaveProjectConstant")
	private List<EmployeeLeave> employeeleaves;
	
	public ProjectConstant() {
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

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<EmployeeLeave> getEmployeeleaves() {
		return this.employeeleaves;
	}

	public void setEmployeeleaves(List<EmployeeLeave> employeeleaves) {
		this.employeeleaves = employeeleaves;
	}
}