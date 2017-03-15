package com.kstrata.apps.hrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kstrata.apps.hrm.dao.ProjectConstantDAO;
import com.kstrata.apps.hrm.model.ProjectConstant;
import com.kstrata.apps.hrm.service.ProjectConstantService;

@Service
public class ProjectConstantServiceImpl implements ProjectConstantService {

	@Autowired
	private ProjectConstantDAO projectConstantDAO;

	@Override
	public List<ProjectConstant> getProjectConstantsByType(String type) {
		return projectConstantDAO.getProjectConstantsByType(type);
	}
	

}
