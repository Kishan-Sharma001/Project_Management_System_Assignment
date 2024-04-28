package com.sirma.projectManagementSystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirma.projectManagementSystem.exception.ProjectNotFoundException;
import com.sirma.projectManagementSystem.model.Project;
import com.sirma.projectManagementSystem.repository.ProjectRepository;
import com.sirma.projectManagementSystem.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	
	@Autowired
	private ProjectRepository projectRepo;
	
	public ProjectServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public ProjectServiceImpl(ProjectRepository projectRepo2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Project> getProjectsList() {
		
		return projectRepo.findAll();
	}

	@Override
	public Project getProjectById(Long projectId) {
		// TODO Auto-generated method stub
		return projectRepo.findById(projectId)
				.orElseThrow(() -> new ProjectNotFoundException("Project not found with the given ID."));
	}

	@Override
	public Project addProject(Project project) {
		
		return projectRepo.save(project);
	}

	@Override
	public Project updateProject(Project project) {
		return projectRepo.save(project);

	}

	@Override
	public void deleteProject(Long projectId) {
		projectRepo.deleteById(projectId);

	}

}
