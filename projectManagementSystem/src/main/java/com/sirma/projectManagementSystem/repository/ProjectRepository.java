package com.sirma.projectManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.projectManagementSystem.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
