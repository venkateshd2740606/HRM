package com.kstrata.apps.hrm.service;

import java.util.List;

import com.kstrata.apps.hrm.model.Employee;

public interface EmployeeService {

	public Employee getEmployeeById(Long empId);

	public List<Employee> getAllEmployees();

	public int getHolidaysFromJoinedDate(Employee employee);
	
	public void saveEmployee(Employee employee);
}
