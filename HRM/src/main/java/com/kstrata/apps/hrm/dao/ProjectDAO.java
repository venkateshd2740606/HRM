package com.kstrata.apps.hrm.dao;

import java.util.List;
import java.util.Set;

import com.kstrata.apps.hrm.model.Project;

public interface ProjectDAO {

	public Project saveProject(final Project project);

	public void deleteProject(final Project project);

	public Project getProjectById(Integer id);

	public List<Project> getProjects(Set<Integer> projectIds);

	public List<Project> findByProjectName(final String projectName);

	public List<Project> findByProjectDesc(final String projectDesc);

	public List<Project> findAll();

	public List<Project> findActiveProjects();

	public List<Project> getActiveProjectsByClient(Integer clientId);

	public void deactivateProject(final Project project);
}
