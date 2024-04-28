package com.sirma.projectManagementSystem.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Project name can't be left empty")
	private String name;
	
	@NotEmpty(message = "project must have description")
	private String description;
	
	@NotNull(message = "Enter a Start Date of Project")
	private Date startDate;
	
	@NotNull(message = "Enter a End Date of Project")
	private Date endDate;

}
