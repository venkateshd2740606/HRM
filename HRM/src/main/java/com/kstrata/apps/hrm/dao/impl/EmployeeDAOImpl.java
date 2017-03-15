package com.kstrata.apps.hrm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kstrata.apps.hrm.dao.EmployeeDAO;
import com.kstrata.apps.hrm.model.Employee;

@Repository
public class EmployeeDAOImpl extends BaseDAOImpl implements EmployeeDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public Employee findEmployeeByLoginName(String loginName) {
		Query query = getEntityManager().createQuery("select e from Employee e where lower(e.empUsername) = (lower(:userName)) and e.active= 'Y' ");
		query.setParameter("userName", loginName);
		List<Employee> employees = query.getResultList();
		if (!employees.isEmpty()) {
			return employees.get(0);
		}
		return null;
	}

	@Override
	public void saveEmployee(final Employee employee) {
		getEntityManager().merge(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployeeById(Long empId) {
		Query query = getEntityManager().createQuery("select e from Employee e where e.empId = :empId and e.active= 'Y' ");
		query.setParameter("empId", empId);
		List<Employee> employees = query.getResultList();
		if (!employees.isEmpty()) {
			return employees.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		Query query = getEntityManager().createQuery("select e from Employee e where e.active = 'Y'");
		List<Employee> employees = query.getResultList();
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getHolidaysFromJoinedDate(Employee employee) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String dateStringTo = dateFormat.format(new Date());
		String dateStringFrom = dateFormat.format(employee.getJoinedDate());
		Query query = getEntityManager().createNativeQuery("SELECT id FROM holidays WHERE date > '" + dateStringFrom + "' AND date < '" + dateStringTo + "' ");
		List<Object> holidays = query.getResultList();
		return holidays.size();
	}
	
}
