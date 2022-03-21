package br.com.ajx.portfoliosb.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajx.portfoliosb.model.entities.Project;
import br.com.ajx.portfoliosb.model.repositories.ProjectRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	/*@PostMapping
	public @ResponseBody Project newProject(@Valid Project project) {
		projectRepository.save(project);
		return project;
	}*/
	
	// Para utilizar apenas uma função para inserir e alterar
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public Project saveProject(@Valid Project project) {
		projectRepository.save(project);
		return project;
	}
	
	@GetMapping
	public Iterable<Project> getAllProjects() {
		return projectRepository.findAll();
	}
	
	@GetMapping(path = "/name/{partName}")
	public Iterable<Project> getProjectsByName(@PathVariable String partName) {
		return projectRepository.findByNameContainingIgnoreCase(partName);
	}
	
	@GetMapping(path = "/page/{numberPage}")
	public Iterable<Project> getProjectsByPage(
			@PathVariable int numberPage) {
		Pageable page = PageRequest.of(numberPage, 5);
		return projectRepository.findAll(page);
	}
	
	@GetMapping(path="/{id}")
	public Optional<Project> getProjectByID(@PathVariable int id) {
		return projectRepository.findById(id);
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteProject(@PathVariable int id) {
		projectRepository.deleteById(id);
	}
	
	/*@PutMapping
	public Project updateProject(@Valid Project project) {
		projectRepository.save(project);
		return project;
	}*/
	
	/* Passando atributos como parâmetro de criação
	  
	   @PostMapping
		public @ResponseBody Project newProject(
				@RequestParam String name,
				@RequestParam String language
		) {
			Project project = new Project(name, language);
			projectRepository.save(project);
			return project;
		}
	  
	 */
	
}
