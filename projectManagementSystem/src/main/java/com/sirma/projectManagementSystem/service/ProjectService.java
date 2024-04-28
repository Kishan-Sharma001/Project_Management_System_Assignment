package com.sirma.projectManagementSystem.service;

import java.util.List;

import com.sirma.projectManagementSystem.model.Project;



public interface ProjectService {

	
	List<Project> getProjectsList();

	Project getProjectById(Long projectId);
	
	Project addProject(Project project);

	Project updateProject(Project project);

	void deleteProject(Long projectId);
}
