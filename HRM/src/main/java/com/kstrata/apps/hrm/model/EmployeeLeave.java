package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employeeleaves database table.
 * 
 */
@Entity
@Table(name="employeeleaves")
@NamedQueries({
		@NamedQuery(name = "EmployeeLeave.findAll", query = "SELECT e FROM EmployeeLeave e"),
		@NamedQuery(name = "EmployeeLeave.findAllByStatus", query = "SELECT e FROM EmployeeLeave e where lower(e.statusProjectConstant.name) = :status order by e.employee.empFirstName"),
		@NamedQuery(name = "EmployeeLeave.findById", query = "SELECT e FROM EmployeeLeave e where e.id = :id"),
		@NamedQuery(name = "EmployeeLeave.getLeavesByEmployee", query = "SELECT e FROM EmployeeLeave e where e.employee.empId = :empId and e.statusProjectConstant.name in (:status)") 
})
public class EmployeeLeave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=100)
	private String attachment;

	@Lob
	private byte[] comments;

	@Transient
	private Date dateEnd;

	@Transient
	private Date dateStart;
	
	@Column(name="date_start")
	private Timestamp startDate;

	@Column(name="date_end")
	private Timestamp endDate;

	private double days;

	@Lob
	private byte[] reason;

	@ManyToOne
	@JoinColumn(name="status_type_constant_id", nullable=false)
	private ProjectConstant statusProjectConstant;

	//bi-directional many-to-one association to Employeeleaveday
	@OneToMany(mappedBy="employeeleave")
	private List<EmployeeLeaveDay> employeeleavedays;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="emp_id", nullable=false)
	@Fetch(FetchMode.SELECT)
	private Employee employee;

	//bi-directional many-to-one association to Period
	@ManyToOne
	@JoinColumn(name="leave_period", nullable=false)
	private Period period;

	//bi-directional many-to-one association to ProjectConstant
	@ManyToOne
	@JoinColumn(name="leave_type_constant_id", nullable=false)
	private ProjectConstant leaveProjectConstant;
	
	@Transient
	private String commentsString;
	
	@Transient
	private String reasonString;
	
	@Transient
	private String mgrStatus;
	
	@Transient
	private boolean editMode = false;

	public EmployeeLeave() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public byte[] getComments() {
		return this.comments;
	}

	public void setComments(byte[] comments) {
		this.comments = comments;
	}

	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	
	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public double getDays() {
		return this.days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	public byte[] getReason() {
		return this.reason;
	}

	public void setReason(byte[] reason) {
		this.reason = reason;
	}

	public List<EmployeeLeaveDay> getEmployeeleavedays() {
		return this.employeeleavedays;
	}

	public void setEmployeeleavedays(List<EmployeeLeaveDay> employeeleavedays) {
		this.employeeleavedays = employeeleavedays;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public String getCommentsString() {
		if (comments != null) {
			return new String(comments);
		}
		return null;
	}

	public void setCommentsString(String commentsString) {
		this.commentsString = commentsString;
	}

	public String getReasonString() {
		if (reason != null) {
			return new String(reason);
		}
		return null;
	}

	public void setReasonString(String reasonString) {
		this.reasonString = reasonString;
	}

	public boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public String getMgrStatus() {
		return mgrStatus;
	}

	public void setMgrStatus(String mgrStatus) {
		this.mgrStatus = mgrStatus;
	}

	public ProjectConstant getStatusProjectConstant() {
		return statusProjectConstant;
	}

	public void setStatusProjectConstant(ProjectConstant statusProjectConstant) {
		this.statusProjectConstant = statusProjectConstant;
	}

	public ProjectConstant getLeaveProjectConstant() {
		return leaveProjectConstant;
	}

	public void setLeaveProjectConstant(ProjectConstant leaveProjectConstant) {
		this.leaveProjectConstant = leaveProjectConstant;
	}
	
	

}