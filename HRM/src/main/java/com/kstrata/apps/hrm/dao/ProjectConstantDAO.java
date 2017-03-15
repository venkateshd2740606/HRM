package com.kstrata.apps.hrm.dao;

import java.util.List;

import com.kstrata.apps.hrm.model.ProjectConstant;

public interface ProjectConstantDAO {

	public List<ProjectConstant> getProjectConstantsByType(String type);
	
}
