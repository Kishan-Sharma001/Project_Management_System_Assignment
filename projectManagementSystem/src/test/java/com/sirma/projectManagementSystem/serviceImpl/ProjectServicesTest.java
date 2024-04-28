
package com.sirma.projectManagementSystem.serviceImpl;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sirma.projectManagementSystem.model.Project;
import com.sirma.projectManagementSystem.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
@DataJpaTest
//@AutoConfigureTestDatabase
class ProjectServicesTest {

//	@Autowired
//	private ProjectRepository projectRepo;
	
	@Mock
	private ProjectRepository projectRepo;
	
	@InjectMocks
	private ProjectServiceImpl projectService;
	
	AutoCloseable autoCloseable;
	Project project;
	
	
	 Project project1= new Project(101L,"Complete Cloud training","Complete Ongoing Cloud Training given time",new Date(2023-12-01),new Date(2024-02-01));
		Project project2= new Project(102L,"Perform Internal Audit","conduct internal Audit",new Date(2024-02-02),new Date(2024-02-06));
		
	@BeforeEach
	void setUp() throws Exception {
		
		 autoCloseable=MockitoAnnotations.openMocks(this);
		
	//projectService = new ProjectServiceImpl(projectRepo);
		
	
		
	}

	
	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	
	@Test
	void testGetProjectsList() {
		List<Project> projects = new ArrayList<>(Arrays.asList(project1,project2));
		when(projectRepo.findAll()).thenReturn(projects);
		assertThat(projectService.getProjectsList()).isEqualTo(projects);
	}

	
	@Test
	void testGetProjectById() {
		
		when(projectRepo.findById(101L)).thenReturn(Optional.ofNullable(project1));
		assertThat(projectService.getProjectById(101L)).isEqualTo(project1);;
	}


	@Test
	public void testAddProject() {
		
		
		mock(Project.class);
		mock(ProjectRepository.class);
		
		project =new Project(103L,"Complete Cloud training","Complete Ongoing Cloud Training given time",new Date(2023-12-01),new Date(2024-02-01));
		
		when(projectRepo.save(project)).thenReturn(project);
		Project savedProject = projectService.addProject(project); // Invoke the method under test
	
		
		   assertNotNull(savedProject);
		//assertThat(projectService.addProject(project)).isEqualTo(null);
	}


	@Test
	void testUpdateProject() {
		
		mock(Project.class);
		mock(ProjectRepository.class);
		
		project =new Project(103L,"erp training","Complete Ongoing Cloud Training given time",new Date(2023-12-01),new Date(2024-02-01));
		
		when(projectRepo.save(project)).thenReturn(project);
		Project updated= projectService.updateProject(project); // Invoke the method under test
	
		
		assertNotNull(updated);
		   assertThat(projectService.updateProject(project)).isEqualTo(project);
	}

	
//	@Test
//	void testDeleteProject() {
//		fail("Not yet implemented");
//	}

}
