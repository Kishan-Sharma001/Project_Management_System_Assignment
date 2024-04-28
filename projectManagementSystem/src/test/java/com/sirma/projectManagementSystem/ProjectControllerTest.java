package com.sirma.projectManagementSystem;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sirma.projectManagementSystem.controller.ProjectController;
import com.sirma.projectManagementSystem.model.Project;
import com.sirma.projectManagementSystem.repository.ProjectRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase
@AutoConfigureMockMvc
//@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private ProjectRepository projectRepo;

	@InjectMocks
	private ProjectController projectController;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();;

    Project project1= new Project(101L,"Complete Cloud training","Complete Ongoing Cloud Training given time",new Date(2023-12-01),new Date(2024-02-01));
	Project project2= new Project(102L,"Perform Internal Audit","conduct internal Audit",new Date(2024-02-02),new Date(2024-02-06));
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
		
		
	}
	
	@Test
	public void testCreateProject() throws Exception {
		Project project = new Project(1L, "project3", "project3 description", new Date(2023-12-01),
				new Date(2024-02-01));
		when(projectRepo.save(project)).thenReturn(project);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/projects")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(project));

		mockMvc.perform(mockRequest).andExpect(status().isCreated());
	}

	@Test
    public void testGetAllProjects() throws Exception {
   
		List<Project> projects = new ArrayList<>(Arrays.asList(project1,project2));

        when(projectRepo.findAll()).thenReturn(projects);

        mockMvc.perform(get("/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("project3"))
                ;
    }

	@Test
    public void testGetProjectById() throws Exception {
        
        when(projectRepo.findById(1L)).thenReturn(Optional.ofNullable(project1));

        mockMvc.perform(get("/projects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("project3"));
    }



    @Test
    public void testUpdateProject() throws Exception {
    	Project project = new Project(2L, "ERP for client XYZ", "erp for accounting and inventory management", new Date(2023-12-01),
				new Date(2024-02-01));
        	when(projectRepo.save(project)).thenReturn(project);
    		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/projects/101")
    				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(project));

    		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("ERP for client XYZ"));
    }

    @Test
    public void testDeleteProject() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/103/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
