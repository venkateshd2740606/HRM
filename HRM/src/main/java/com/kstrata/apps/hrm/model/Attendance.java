package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the attendance database table.
 * 
 */
@Entity
@Table(name="attendance")
@NamedQuery(name="findAll", query="SELECT a FROM Attendance a")
public class Attendance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=1000)
	private String comments;

	private BigDecimal hours;

	@Column(name="IN_TIME", nullable=false)
	private Timestamp inTime;

	@Column(name="OUT_TIME", nullable=false)
	private Timestamp outTime;

	@Column(length=20)
	private String status;
	
	@Column(length=20)
	private String date;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EMPLOYEE", nullable=false)
	private Employee employee;
	
	@Column(name="SUBMIT_STATUS", nullable=false)
	private String submitStatus;
	
	@Transient
	private Integer inHour;
	
	@Transient
	private Integer inMinute;
	
	@Transient
	private Integer outHour;
	
	@Transient
	private Integer outMinute;

	public Attendance() {
		setInHour(9);
		setInMinute(00);
		setOutHour(17);
		setOutMinute(30);
		setStatus("Available");
		setHours(BigDecimal.valueOf(8.30));
		setSubmitStatus("N");
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getHours() {
		return this.hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public Timestamp getInTime() {
		return this.inTime;
	}

	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}

	public Timestamp getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employeeBean) {
		this.employee = employeeBean;
	}

	public Integer getInHour() {
		Calendar calendar = getCalendar(getInTime());
		if (calendar != null) {
			inHour = calendar.get(Calendar.HOUR_OF_DAY);
		}
		return inHour;
	}

	public void setInHour(Integer inHour) {
		this.inHour = inHour;
	}

	public Integer getInMinute() {
		Calendar calendar = getCalendar(getInTime());
		if (calendar != null) {
			inMinute = calendar.get(Calendar.MINUTE);
		}
		return inMinute;
	}

	public void setInMinute(Integer inMinute) {
		this.inMinute = inMinute;
	}

	public Integer getOutHour() {
		Calendar calendar = getCalendar(getOutTime());
		if (calendar != null) {
			outHour = calendar.get(Calendar.HOUR_OF_DAY);
		}
		return outHour;
	}

	public void setOutHour(Integer outHour) {
		this.outHour = outHour;
	}

	public Integer getOutMinute() {
		Calendar calendar = getCalendar(getOutTime());
		if (calendar != null) {
			outMinute = calendar.get(Calendar.MINUTE);
		}
		return outMinute;
	}

	public void setOutMinute(Integer outMinute) {
		this.outMinute = outMinute;
	}
	
	private Calendar getCalendar(Timestamp time) {
		if (time == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(time.getTime()));;
		return calendar;
	}
	
	public Integer getOrgInHour() {
		return inHour;
	}
	
	public Integer getOrgInMinute() {
		return inMinute;
	}
	
	public Integer getOrgOutHour() {
		return outHour;
	}
	
	public Integer getOrgOutMinute() {
		return outMinute;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}
	
}