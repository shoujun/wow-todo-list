/**
 * 
 */
package com.wownetwork.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wownetwork.todolist.model.Project;
import com.wownetwork.todolist.repository.ProjectRepository;

/**
 * @author ShouJun
 *
 */
@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;

	/**
	 * Default constructor
	 * 
	 * @param projectRepository
	 */
	@Autowired
	public ProjectServiceImpl(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public Project findById(Long id) {
		return projectRepository.findById(id).orElseGet(() -> null);
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Project save(Project project) {
		return projectRepository.save(project);
	}

}
