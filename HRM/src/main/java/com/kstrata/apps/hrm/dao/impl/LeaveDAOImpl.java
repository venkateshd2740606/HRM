package com.kstrata.apps.hrm.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kstrata.apps.hrm.dao.LeaveDAO;
import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.model.EmployeeLeave;

@Repository
public class LeaveDAOImpl extends BaseDAOImpl implements LeaveDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public EmployeeLeave getEmployeeLeaveById(Long id) {
		Query query = getEntityManager().createNamedQuery("EmployeeLeave.findById");
		query.setParameter("id", id);
		List<EmployeeLeave> employeeLeavs = query.getResultList();
		if (!employeeLeavs.isEmpty()) {
			return employeeLeavs.get(0);
		}
		return null;
	}

	@Override
	public void saveOrUpdate(EmployeeLeave employeeLeave) {
		getEntityManager().merge(employeeLeave);
		getEntityManager().merge(employeeLeave.getEmployee());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeLeave> getLeavesByEmployee(Employee employee, List<String> statusList) {
		Query query = getEntityManager().createNamedQuery("EmployeeLeave.getLeavesByEmployee");
		query.setParameter("empId", employee.getEmpId());
		query.setParameter("status", statusList);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeLeave> getEmployeeLeavesByStatus(String status) {
		String statusInLower = status.toLowerCase();
		Query query = getEntityManager().createNamedQuery("EmployeeLeave.findAllByStatus");
		query.setParameter("status", statusInLower);
		return query.getResultList();
	}

	@Override
	public void deleteEmployeeLeave(EmployeeLeave employeeLeave) {
		EmployeeLeave empLeave = getEmployeeLeaveById(employeeLeave.getId());
		if (empLeave != null) {
			getEntityManager().remove(empLeave);
		}
	}

	@Override
	public void updateEmployeeLeave(EmployeeLeave employeeLeave) {
		getEntityManager().merge(employeeLeave);
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getTotalLeavesByType(Employee employee, String leaveTypeShortName, String status) {
		Query query = getEntityManager().createQuery("select el from EmployeeLeave el where el.employee.empId=:empId and el.leaveProjectConstant.id=(select p.id from ProjectConstant p where p.type='LEAVE' and p.shortName=:shortName) and el.statusProjectConstant.name=:status");
		query.setParameter("empId", employee.getEmpId());
		query.setParameter("shortName", leaveTypeShortName);
		query.setParameter("status", status);
		List<EmployeeLeave> employeeLeaves = query.getResultList();
		
		double leaves = 0.0;
		for (EmployeeLeave employeeLeave : employeeLeaves) {
			leaves = leaves + employeeLeave.getDays();
		}
		return leaves;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeLeave> getEmployeePassedLeaves(Employee emp) {
		Query query = getEntityManager().createQuery("select el from EmployeeLeave el where el.employee.empId=:empId and el.startDate <= :currentDate");
		query.setParameter("empId", emp.getEmpId());
		query.setParameter("currentDate", new Timestamp(new Date().getTime()));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeLeave> getEmployeeCurrentLeaves(Employee emp) {
		Query query = getEntityManager().createQuery("select el from EmployeeLeave el where el.employee.empId=:empId and el.startDate >= :currentDate");
		query.setParameter("empId", emp.getEmpId());
		query.setParameter("currentDate", new Timestamp(new Date().getTime()));
		return query.getResultList();
	}

}
