package com.kstrata.apps.hrm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kstrata.apps.hrm.dao.LeaveDAO;
import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.model.EmployeeLeave;
import com.kstrata.apps.hrm.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired
	private LeaveDAO leaveDAO;
	
	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdate(EmployeeLeave employeeLeave) {
		leaveDAO.saveOrUpdate(employeeLeave);
	}

	@Override
	public List<EmployeeLeave> getLeavesByEmployee(Employee employee, List<String> statusList) {
		return leaveDAO.getLeavesByEmployee(employee, statusList);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteEmployeeLeave(EmployeeLeave employeeLeave) {
		leaveDAO.deleteEmployeeLeave(employeeLeave);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateEmployeeLeave(EmployeeLeave employeeLeave) {
		leaveDAO.updateEmployeeLeave(employeeLeave);
	}

	@Override
	public double getTotalLeavesByType(Employee employee, String leaveTypeShortName, String status) {
		return leaveDAO.getTotalLeavesByType(employee, leaveTypeShortName, status);
	}

	@Override
	public List<EmployeeLeave> getEmployeeLeavesByStatus(String status) {
		return leaveDAO.getEmployeeLeavesByStatus(status);
	}

	@Override
	public EmployeeLeave getEmployeeLeaveById(Long leaveId) {
		return leaveDAO.getEmployeeLeaveById(leaveId);
	}

	@Override
	public List<EmployeeLeave> getEmployeePassedLeaves(Employee emp) {
		return leaveDAO.getEmployeePassedLeaves(emp);
	}

	@Override
	public List<EmployeeLeave> getEmployeeCurrentLeaves(Employee emp) {
		return leaveDAO.getEmployeeCurrentLeaves(emp);
	}
	

}
