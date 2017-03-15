package com.kstrata.apps.hrm.dao;

import java.util.List;

import com.kstrata.apps.hrm.model.Employee;

public interface EmployeeDAO {
	
	Employee findEmployeeByLoginName(final String loginName);
	
	void saveEmployee(final Employee employee);

	Employee getEmployeeById(Long empId);

	List<Employee> getAllEmployees();

	int getHolidaysFromJoinedDate(Employee employee);

}
