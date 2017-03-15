package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the timesheet database table.
 * 
 */
@Entity
@Table(name="timesheet")
@NamedQuery(name="Timesheet.findAll", query="SELECT t FROM TimeSheet t")
public class TimeSheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TIMESHEET_ID", unique=true, nullable=false)
	private Long timesheetId;

	@Lob
	private byte[] comments;

	@Column(name="END_TIME", nullable=false)
	private Timestamp endTime;

	@Column(name="MNGR_STATUS", nullable=false, length=8)
	private String mngrStatus;

	@Column(name="START_TIME", nullable=false)
	private Timestamp startTime;

	@Column(nullable=false, length=11)
	private String status;

	@Column(name="SUBMIT_FLAG", nullable=false, length=20)
	private String submitFlag;

	@Column(name="TASK_DATE", nullable=false)
	private Timestamp taskDate;

	@Lob
	@Column(name="TASK_DESC", nullable=false)
	private byte[] taskDesc;

	@Column(name="TASK_TYPE", length=2)
	private String taskType;

	@Column(nullable=false, precision=10, scale=2)
	private Double total;

	//bi-directional many-to-one association to EmployeeProject
	@ManyToOne
	@JoinColumn(name="EMP_PROJECT_ID", nullable=false)
	private EmployeeProject employeeProject;

	//bi-directional many-to-one association to Period
	@ManyToOne
	@JoinColumn(name="PERIOD_ID", nullable=false)
	private Period period;

	public TimeSheet() {
	}

	public Long getTimesheetId() {
		return this.timesheetId;
	}

	public void setTimesheetId(Long timesheetId) {
		this.timesheetId = timesheetId;
	}

	public byte[] getComments() {
		return this.comments;
	}

	public void setComments(byte[] comments) {
		this.comments = comments;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getMngrStatus() {
		return this.mngrStatus;
	}

	public void setMngrStatus(String mngrStatus) {
		this.mngrStatus = mngrStatus;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitFlag() {
		return this.submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}

	public Timestamp getTaskDate() {
		return this.taskDate;
	}

	public void setTaskDate(Timestamp taskDate) {
		this.taskDate = taskDate;
	}

	public byte[] getTaskDesc() {
		return this.taskDesc;
	}

	public void setTaskDesc(byte[] taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public EmployeeProject getEmployeeProject() {
		return this.employeeProject;
	}

	public void setEmployeeProject(EmployeeProject employeeProject) {
		this.employeeProject = employeeProject;
	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

}