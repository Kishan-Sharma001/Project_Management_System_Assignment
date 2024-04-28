package com.sirma.projectManagementSystem.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sirma.projectManagementSystem.model.Project;
import com.sirma.projectManagementSystem.serviceImpl.ProjectServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name="Project Controller" ,description ="this is project crud api")
//@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	
	@Operation(summary="get lsit of projects",description="project api -List of Projects")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",description="Sucess | OK"),
			@ApiResponse(responseCode="401",description="notAuthorized"),
			@ApiResponse(responseCode="201",description="new project created")
			
	})
	@GetMapping("/projects")
		public List<Project> getAllProjects() {
		return projectServiceImpl.getProjectsList();

	};

	@GetMapping("/projects/{id}")
	@Operation(summary="get projects by id",description="project api - Find project by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",description="Sucess | OK"),
			@ApiResponse(responseCode="401",description="notAuthorized"),
			@ApiResponse(responseCode="201",description="new project created")
			
	})
	public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
		Project project =projectServiceImpl.getProjectById(id);
		return ResponseEntity.ok(project);
	}

	@PostMapping("/projects")
	@Operation(summary="create/add Project",description="project api -create new project")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",description="Sucess | OK"),
			@ApiResponse(responseCode="401",description="notAuthorized"),
			@ApiResponse(responseCode="201",description="new project created")
			
	})
	public ResponseEntity<Void> createProject(@Valid @RequestBody Project project) {

		Project createdProject = projectServiceImpl.addProject(project);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProject.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("projects/{id}")
	@Operation(summary="update project details",description="project api - edit project details")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",description="Sucess | OK"),
			@ApiResponse(responseCode="401",description="notAuthorized"),
			@ApiResponse(responseCode="201",description="new project created")
			
	})
	public ResponseEntity<Project> updateProject(@PathVariable("id") long id , @Valid @RequestBody Project project){
		
		projectServiceImpl.updateProject(project);
		return new ResponseEntity<Project>(project ,HttpStatus.OK);
	}

	@DeleteMapping("projects/{id}/delete")
	@Operation(summary="delete Project",description="project api - delete project")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",description="Sucess | OK"),
			@ApiResponse(responseCode="401",description="notAuthorized"),
			@ApiResponse(responseCode="201",description="new project created")
			
	})
	public ResponseEntity<Void> deleteProject(@PathVariable("id") long id) {

		projectServiceImpl.deleteProject(id);
		return ResponseEntity.noContent().build();
	}

}
