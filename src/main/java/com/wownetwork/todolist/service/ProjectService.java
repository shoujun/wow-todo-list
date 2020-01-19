/**
 * 
 */
package com.wownetwork.todolist.service;

import java.util.List;

import com.wownetwork.todolist.model.Project;

/**
 * @author ShouJun
 *
 */
public interface ProjectService {

	/**
	 * Finds the project by given id
	 *
	 * @param id
	 * @return
	 */
	Project findById(Long id);

	/**
	 * Finds list of projects
	 * 
	 * @return
	 */
	List<Project> findAll();

	/**
	 * Creates or updates a project
	 * 
	 * @param project
	 * @return
	 */
	Project save(Project project);

}
