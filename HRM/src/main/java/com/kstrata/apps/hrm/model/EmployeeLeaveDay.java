package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the employeeleavedays database table.
 * 
 */
@Entity
@Table(name="employeeleavedays")
@NamedQuery(name="Employeeleaveday.findAll", query="SELECT e FROM EmployeeLeaveDay e")
public class EmployeeLeaveDay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="leave_date")
	private Date leaveDate;

	@Column(name="leave_type", nullable=false, length=1)
	private String leaveType;

	//bi-directional many-to-one association to Employeeleave
	@ManyToOne
	@JoinColumn(name="employee_leave", nullable=false)
	private EmployeeLeave employeeleave;

	public EmployeeLeaveDay() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getLeaveType() {
		return this.leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public EmployeeLeave getEmployeeleave() {
		return this.employeeleave;
	}

	public void setEmployeeleave(EmployeeLeave employeeleave) {
		this.employeeleave = employeeleave;
	}

}