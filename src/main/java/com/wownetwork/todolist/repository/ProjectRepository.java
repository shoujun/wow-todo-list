/**
 * 
 */
package com.wownetwork.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wownetwork.todolist.model.Project;

/**
 * @author ShouJun
 *
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
