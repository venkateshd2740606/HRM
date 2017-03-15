package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EMP_ID", unique=true, nullable=false)
	private Long empId;

	@Column(nullable=false, length=1)
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean active;

	@Column(length=1000)
	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(length=100)
	private String city;

	@Temporal(TemporalType.DATE)
	@Column(name="CONFIRMATION_DATE")
	private Date confirmationDate;

	@Column(length=20)
	private String country;
	
	@Column(name="AL_BAL")
	private Double annualLeaveBal;
	
	@Column(name="SL_BAL")
	private Double sickLeaveBal;

	private BigInteger department;

	@Column(name="EMP_DESIGNATION", nullable=false, length=40)
	private String empDesignation;

	@Column(name="EMP_EMAILID", nullable=false, length=250)
	private String empEmailid;

	@Column(name="EMP_FIRST_NAME", nullable=false, length=40)
	private String empFirstName;

	@Column(name="EMP_LAST_NAME", nullable=false, length=40)
	private String empLastName;

	@Lob
	@Column(name="EMP_PASSWORD", nullable=false)
	private byte[] empPassword;

	@Column(name="EMP_ROLE_ID", nullable=false)
	private Long empRoleId;

	@Column(name="EMP_USERNAME", nullable=false, length=20)
	private String empUsername;

	@Column(name="EMPLOYEE_REFERENCE", length=100)
	private String employeeReference;

	@Column(name="EMPLOYMENT_STATUS", length=20)
	private String employmentStatus;

	@Column(length=20)
	private String gender;

	@Column(name="HOME_PHONE", length=20)
	private String homePhone;

	@Temporal(TemporalType.DATE)
	@Column(name="JOINED_DATE")
	private Date joinedDate;

	@Column(name="MARITAL_STATUS", length=20)
	private String maritalStatus;

	@Column(name="MOBILE_PHONE", length=20)
	private String mobilePhone;

	@Column(name="PASSWORD_CHANGE_FLAG", length=1)
	private String passwordChangeFlag;

	@Column(name="PAY_GRADE")
	private BigInteger payGrade;

	@Column(name="PRIVATE_EMAIL", length=100)
	private String privateEmail;

	@Column(name="REMINDER_ENABLE", nullable=false, length=20)
	private String reminderEnable;

	private Long supervisor;

	@Column(name="WORK_PHONE", length=20)
	private String workPhone;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="employee")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to EmployeeProject
	@OneToMany(mappedBy="employee")
	private List<EmployeeProject> employeeProjects;

	//bi-directional many-to-one association to Employeeleave
	@OneToMany(mappedBy="employee")
	private List<EmployeeLeave> employeeleaves;
	
	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="emp_role_id")
	private Role role;

	public Employee() {
	}

	public Long getEmpId() {
		return this.empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getConfirmationDate() {
		return this.confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigInteger getDepartment() {
		return this.department;
	}

	public void setDepartment(BigInteger department) {
		this.department = department;
	}

	public String getEmpDesignation() {
		return this.empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpEmailid() {
		return this.empEmailid;
	}

	public void setEmpEmailid(String empEmailid) {
		this.empEmailid = empEmailid;
	}

	public String getEmpFirstName() {
		return this.empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return this.empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public byte[] getEmpPassword() {
		return this.empPassword;
	}

	public void setEmpPassword(byte[] empPassword) {
		this.empPassword = empPassword;
	}

	public Long getEmpRoleId() {
		return this.empRoleId;
	}

	public void setEmpRoleId(Long empRoleId) {
		this.empRoleId = empRoleId;
	}

	public String getEmpUsername() {
		return this.empUsername;
	}

	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}

	public String getEmployeeReference() {
		return this.employeeReference;
	}

	public void setEmployeeReference(String employeeReference) {
		this.employeeReference = employeeReference;
	}

	public String getEmploymentStatus() {
		return this.employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Date getJoinedDate() {
		return this.joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPasswordChangeFlag() {
		return this.passwordChangeFlag;
	}

	public void setPasswordChangeFlag(String passwordChangeFlag) {
		this.passwordChangeFlag = passwordChangeFlag;
	}

	public BigInteger getPayGrade() {
		return this.payGrade;
	}

	public void setPayGrade(BigInteger payGrade) {
		this.payGrade = payGrade;
	}

	public String getPrivateEmail() {
		return this.privateEmail;
	}

	public void setPrivateEmail(String privateEmail) {
		this.privateEmail = privateEmail;
	}

	public String getReminderEnable() {
		return this.reminderEnable;
	}

	public void setReminderEnable(String reminderEnable) {
		this.reminderEnable = reminderEnable;
	}

	public Long getSupervisor() {
		return this.supervisor;
	}

	public void setSupervisor(Long supervisor) {
		this.supervisor = supervisor;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public List<Attendance> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public List<EmployeeProject> getEmployeeProjects() {
		return this.employeeProjects;
	}

	public void setEmployeeProjects(List<EmployeeProject> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}

	public List<EmployeeLeave> getEmployeeleaves() {
		return this.employeeleaves;
	}

	public void setEmployeeleaves(List<EmployeeLeave> employeeleaves) {
		this.employeeleaves = employeeleaves;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Double getAnnualLeaveBal() {
		return annualLeaveBal;
	}

	public void setAnnualLeaveBal(Double annualLeaveBal) {
		this.annualLeaveBal = annualLeaveBal;
	}

	public Double getSickLeaveBal() {
		return sickLeaveBal;
	}

	public void setSickLeaveBal(Double sickLeaveBal) {
		this.sickLeaveBal = sickLeaveBal;
	}
	
	
}