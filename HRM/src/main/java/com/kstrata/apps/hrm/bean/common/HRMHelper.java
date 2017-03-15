package com.kstrata.apps.hrm.bean.common;

import static com.kstrata.apps.hrm.bean.util.HRMConstants.AL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.APPROVED;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.HDAL;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kstrata.apps.hrm.bean.util.HRMConstants;
import com.kstrata.apps.hrm.bean.util.HRMUtil;
import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.service.EmployeeService;
import com.kstrata.apps.hrm.service.LeaveService;

@Component
public class HRMHelper {
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	public Employee updateEmployeeAnnualLeaveBal(Employee employee) {
		employee.setAnnualLeaveBal(getEmployeeAnnualBal(employee, 0.0, 0.0));
		employeeService.saveEmployee(employee);
		HRMUtil.putObjectInSession(HRMConstants.LOGGED_USER, employee);
		return employee;
	}
	
	public double getEmployeeAnnualBal(Employee employee, double alLeaveDays, double hdalLeaveDays) {
		//int holidays = employeeService.getHolidaysFromJoinedDate(employee);
		double fullALLeaves = leaveService.getTotalLeavesByType(employee, AL, APPROVED);
		fullALLeaves = fullALLeaves + alLeaveDays;
		double halfDayALLeaves = leaveService.getTotalLeavesByType(employee, HDAL, APPROVED);
		halfDayALLeaves = halfDayALLeaves + hdalLeaveDays;
		double balance = getAnnualLeaveBalance(employee.getJoinedDate(), new Date(), fullALLeaves, halfDayALLeaves);
		return balance;
	}
	
	private double getAnnualLeaveBalance(Date joinedDate, Date currentDate, double fullALLeaves, double halfDayALLeaves) {
		//No of days from joined date to current
		int noOfWorkDays = HRMUtil.daysBetween(joinedDate, currentDate);
		
		int noOfDaysElapsed = noOfWorkDays;// - holidays;
		double totalLeavesTaken = fullALLeaves + halfDayALLeaves;
		
		double leavesAccumulatedPerDay = 0.041;
		double totalLeavesAccumulated = noOfDaysElapsed * leavesAccumulatedPerDay;
		double balance = totalLeavesAccumulated - totalLeavesTaken;
		
		return balance;
	}
}