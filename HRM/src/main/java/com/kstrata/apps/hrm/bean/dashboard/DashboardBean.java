package com.kstrata.apps.hrm.bean.dashboard;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.kstrata.apps.hrm.bean.common.BaseBean;

@ManagedBean
@ViewScoped
public class DashboardBean extends BaseBean {
	
	@PostConstruct
	@Override
	public void init() {
		/*Role role = employee.getRole();
		if (role != null && "Admin".equalsIgnoreCase(role.getRoleName())) {
			//Logged user is Admin
			
		} else {
			//Logged user is Employee
		}*/
	}
}
