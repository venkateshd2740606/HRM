package com.kstrata.apps.hrm.dao;

import java.util.List;

import com.kstrata.apps.hrm.model.Role;

public interface RoleDAO {
	
	public void saveRole(final Role role);

	public void deleteRole(final Role role);

	public Role getRoleById(Integer id);

	public List<Role> findByRoleName(final String roleName);

	public List<Role> findAll();

}
