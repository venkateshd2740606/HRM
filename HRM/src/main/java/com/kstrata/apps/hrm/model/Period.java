package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the period database table.
 * 
 */
@Entity
@Table(name="period")
@NamedQuery(name="Period.findAll", query="SELECT p FROM Period p")
public class Period implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3094951231534500358L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PERIOD_ID", unique=true, nullable=false)
	private Long periodId;

	@Column(name="DATE_FROM", nullable=false)
	private Timestamp dateFrom;

	@Column(name="DATE_TO", nullable=false)
	private Timestamp dateTo;

	//bi-directional many-to-one association to Employeeleave
	@OneToMany(mappedBy="period")
	private List<EmployeeLeave> employeeleaves;

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="period")
	private List<TimeSheet> timesheets;

	public Period() {
	}

	public Long getPeriodId() {
		return this.periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public Timestamp getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	public List<EmployeeLeave> getEmployeeleaves() {
		return this.employeeleaves;
	}

	public void setEmployeeleaves(List<EmployeeLeave> employeeleaves) {
		this.employeeleaves = employeeleaves;
	}

	public List<TimeSheet> getTimesheets() {
		return this.timesheets;
	}

	public void setTimesheets(List<TimeSheet> timesheets) {
		this.timesheets = timesheets;
	}
}