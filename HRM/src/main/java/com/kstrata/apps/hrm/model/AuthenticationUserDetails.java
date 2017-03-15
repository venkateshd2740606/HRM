package com.kstrata.apps.hrm.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6852848492320321571L;
	private final String loginUserName;
	private final String password;
	private final boolean enabled;	
	private Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
	private Employee employee;
	
	public AuthenticationUserDetails(Employee employee) {
		this.loginUserName = employee.getEmpUsername();
		this.password = new String(employee.getEmpPassword());
		this.enabled = true;
		this.grantedAuthorities.add(new SimpleGrantedAuthority(employee.getRole().getRoleName())); 
		this.setEmployee(employee);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return loginUserName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
