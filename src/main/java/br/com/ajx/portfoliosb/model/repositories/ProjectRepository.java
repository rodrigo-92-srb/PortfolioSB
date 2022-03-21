package br.com.ajx.portfoliosb.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ajx.portfoliosb.model.entities.Project;

public interface ProjectRepository 
	
	extends PagingAndSortingRepository<Project, Integer>{
		public Iterable<Project> findByNameContainingIgnoreCase(String partName);
	}

	