package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@Table(name="client")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CLIENT_ID", unique=true, nullable=false)
	private Long clientId;

	@Column(name="CLIENT_NAME", nullable=false, length=100)
	private String clientName;

	@Column(name="GRACE_MINUTES")
	private int graceMinutes;

	@Column(name="LUNCH_MINUTES")
	private int lunchMinutes;

	@Column(name="LUNCHGRACE_FLAG")
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean lunchgraceFlag;

	@Column(length=20)
	private String shortname;

	@Column(name="TIMESHEET_REMINDER_ENABLE", nullable=false, length=20)
	private String timesheetReminderEnable;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="client")
	private List<Project> projects;

	public Client() {
	}

	public Long getClientId() {
		return this.clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getGraceMinutes() {
		return this.graceMinutes;
	}

	public void setGraceMinutes(int graceMinutes) {
		this.graceMinutes = graceMinutes;
	}

	public int getLunchMinutes() {
		return this.lunchMinutes;
	}

	public void setLunchMinutes(int lunchMinutes) {
		this.lunchMinutes = lunchMinutes;
	}

	public boolean getLunchgraceFlag() {
		return this.lunchgraceFlag;
	}

	public void setLunchgraceFlag(boolean lunchgraceFlag) {
		this.lunchgraceFlag = lunchgraceFlag;
	}

	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getTimesheetReminderEnable() {
		return this.timesheetReminderEnable;
	}

	public void setTimesheetReminderEnable(String timesheetReminderEnable) {
		this.timesheetReminderEnable = timesheetReminderEnable;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}