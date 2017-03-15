package com.kstrata.apps.hrm.service;

import java.util.List;

import com.kstrata.apps.hrm.model.ProjectConstant;

public interface ProjectConstantService {

	public List<ProjectConstant> getProjectConstantsByType(String type);
	
}
