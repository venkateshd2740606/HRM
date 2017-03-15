package com.kstrata.apps.hrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kstrata.apps.hrm.dao.EmpDAO;
import com.kstrata.apps.hrm.dao.EmployeeDAO;
import com.kstrata.apps.hrm.model.AuthenticationUserDetails;
import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements UserDetailsService, EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private EmpDAO empDAO;
	
	public EmployeeServiceImpl(){
		
	}
	
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		Employee employee = employeeDAO.findEmployeeByLoginName(loginName);
		throwExceptionIfNotFound(employee, loginName);
		return new AuthenticationUserDetails(employee);
	}
	
	private void throwExceptionIfNotFound(Employee user, String login) {
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + "  has not been found.");
        }
    }
	
	@Override
	public Employee getEmployeeById(Long empId) {
		return employeeDAO.getEmployeeById(empId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public int getHolidaysFromJoinedDate(Employee employee) {
		return employeeDAO.getHolidaysFromJoinedDate(employee);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveEmployee(Employee employee) {
		employeeDAO.saveEmployee(employee);
	}

}
