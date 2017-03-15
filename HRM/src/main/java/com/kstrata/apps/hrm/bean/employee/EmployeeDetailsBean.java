package com.kstrata.apps.hrm.bean.employee;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "employeeDetailsBean")
@ViewScoped
public class EmployeeDetailsBean {

	private String employeeID;
	private String firstName;
	private String middleName;
	private String surname;
	private String residentialAddress;
	private String city;
	private String state;
	private String postCode;
	private String country;
	private String contactNumber;
	private String alternateContactNumber;
	private String employeeDesignation;
	private String companyEmailID;
	private String reportTo;
	private Date joiningDate;
	private String permanentAddress;
	private String permanentCity;
	private String permanentState;
	private String permanentPostCode;
	private String permanentCountry;
	private String personalEmail;
	private String emergencyContact1Name;
	private String emergencyContact1Relation;
	private String emergencyContact1ContactNumber;
	private String emergencyContact2Name;
	private String emergencyContact2Relation;
	private String emergencyContact2ContactNumber;
	private boolean sameAddress;
	
	
	@PostConstruct
	public void init() {
		
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getCompanyEmailID() {
		return companyEmailID;
	}

	public void setCompanyEmailID(String companyEmailID) {
		this.companyEmailID = companyEmailID;
	}

	public String getReportTo() {
		return reportTo;
	}

	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentPostCode() {
		return permanentPostCode;
	}

	public void setPermanentPostCode(String permanentPostCode) {
		this.permanentPostCode = permanentPostCode;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getEmergencyContact1Name() {
		return emergencyContact1Name;
	}

	public void setEmergencyContact1Name(String emergencyContact1Name) {
		this.emergencyContact1Name = emergencyContact1Name;
	}

	public String getEmergencyContact1Relation() {
		return emergencyContact1Relation;
	}

	public void setEmergencyContact1Relation(String emergencyContact1Relation) {
		this.emergencyContact1Relation = emergencyContact1Relation;
	}

	public String getEmergencyContact1ContactNumber() {
		return emergencyContact1ContactNumber;
	}

	public void setEmergencyContact1ContactNumber(
			String emergencyContact1ContactNumber) {
		this.emergencyContact1ContactNumber = emergencyContact1ContactNumber;
	}

	public String getEmergencyContact2Name() {
		return emergencyContact2Name;
	}

	public void setEmergencyContact2Name(String emergencyContact2Name) {
		this.emergencyContact2Name = emergencyContact2Name;
	}

	public String getEmergencyContact2Relation() {
		return emergencyContact2Relation;
	}

	public void setEmergencyContact2Relation(String emergencyContact2Relation) {
		this.emergencyContact2Relation = emergencyContact2Relation;
	}

	public String getEmergencyContact2ContactNumber() {
		return emergencyContact2ContactNumber;
	}

	public void setEmergencyContact2ContactNumber(
			String emergencyContact2ContactNumber) {
		this.emergencyContact2ContactNumber = emergencyContact2ContactNumber;
	}

	public boolean isSameAddress() {
		return sameAddress;
	}

	public void setSameAddress(boolean sameAddress) {
		this.sameAddress = sameAddress;
	}

	public void OnSameAddress(AjaxBehaviorEvent event) {
		if (sameAddress == true) {
			this.permanentAddress = residentialAddress;
			this.permanentCity = city;
			this.permanentCountry = country;
			this.permanentPostCode = postCode;
			this.permanentState = state;
		} else {
			this.permanentAddress = null;
			this.permanentCity = null;
			this.permanentCountry = null;
			this.permanentPostCode = null;
			this.permanentState = null;
			this.sameAddress = false;
		}
	}

	public String saveEmployeeDetails() {
		
		return "navigateToLeaveListing";
	}

}