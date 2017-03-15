package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employee_project database table.
 * 
 */
@Entity
@Table(name="employee_project")
@NamedQuery(name="EmployeeProject.findAll", query="SELECT e FROM EmployeeProject e")
public class EmployeeProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EMP_PROJ_ID", unique=true, nullable=false)
	private Long empProjId;

	@Column(nullable=false, length=1)
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean active;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EMP_ID", nullable=false)
	private Employee employee;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="PROJECT_ID", nullable=false)
	private Project project;

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="employeeProject")
	private List<TimeSheet> timesheets;

	public EmployeeProject() {
	}

	public Long getEmpProjId() {
		return this.empProjId;
	}

	public void setEmpProjId(Long empProjId) {
		this.empProjId = empProjId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<TimeSheet> getTimesheets() {
		return this.timesheets;
	}

	public void setTimesheets(List<TimeSheet> timesheets) {
		this.timesheets = timesheets;
	}
}