package com.kstrata.apps.hrm.dao;

import java.util.List;

import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.model.EmployeeProject;
import com.kstrata.apps.hrm.model.Project;

public interface EmployeeProjectDAO {
	
	public void saveEmployeeProject(final EmployeeProject employeeProject);

	public void insertEmployeeproject(final Project project, final Employee employee);

	public void removeEmployeeproject(final Project project, final Employee employee);
	
	public void deactivateEmployeeProjects(final Project project);

	public void deleteEmployeeProject(final EmployeeProject employeeProject);

	public EmployeeProject findById(Integer id);

	public List<EmployeeProject> findEmployeeProjectByEmployee(final Employee employee, List<Integer> projectIds);

	public List<EmployeeProject> findEmployeeProjectByEmployee(final Employee employee);

	public EmployeeProject findEmployeeProject(final Project project, final Employee employee);

	public List<EmployeeProject> findAll();
}
