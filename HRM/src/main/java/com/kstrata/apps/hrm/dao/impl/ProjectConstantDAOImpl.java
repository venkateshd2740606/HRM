package com.kstrata.apps.hrm.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kstrata.apps.hrm.dao.ProjectConstantDAO;
import com.kstrata.apps.hrm.model.ProjectConstant;

@Repository
public class ProjectConstantDAOImpl extends BaseDAOImpl implements ProjectConstantDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectConstant> getProjectConstantsByType(String type) {
		Query query = getEntityManager().createNamedQuery("ProjectConstant.findByType");
		query.setParameter("type", type);
		return query.getResultList();
	}
	
}
