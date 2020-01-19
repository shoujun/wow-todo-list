/**
 * 
 */
package com.wownetwork.todolist.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wownetwork.todolist.model.Project;
import com.wownetwork.todolist.service.ProjectService;

/**
 * @author ShouJun
 *
 */
@RestController
@RequestMapping(value = "/api/projects")
public class ProjectController {

	// The logger
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	private final ProjectService projectService;

	/**
	 * Constructor
	 * 
	 * @param accountAuthService
	 */
	@Autowired
	public ProjectController(final ProjectService projectService) {
		this.projectService = projectService;
	}

//
// This GET and PUT combined together works very well
//
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.PUT }, value = "/{projectId}")
//	public ResponseEntity<Project> getOrPut(@PathVariable final Long projectId,
//			@RequestBody(required = false) final Project project, HttpServletRequest request) {
//
//		Project existProject = projectService.findById(projectId);
//		if (existProject != null) {
//			if ("GET".equalsIgnoreCase(request.getMethod())) {
//				return ResponseEntity.ok(existProject);
//			} else {
//				if (project != null) {
//					existProject.setName(project.getName());
//				}
//				return ResponseEntity.ok(projectService.save(existProject));
//			}
//		}
//		return ResponseEntity.notFound().build();
//	}

	/**
	 * @param projectId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{projectId}")
	public ResponseEntity<Project> findOne(@PathVariable final Long projectId) {
		Project project = projectService.findById(projectId);
		if (project != null) {
			return ResponseEntity.ok(project);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Project>> findAll() {
		return ResponseEntity.ok(projectService.findAll());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody @Valid final Project project) throws URISyntaxException {

		if (project == null) {
			throw new IllegalArgumentException("Received null project");
		}
		try {
			// Save to database
			Project savedProject = projectService.save(project);
			return ResponseEntity.created(new URI("/api/projects/" + savedProject.getId())).build();
		} catch (Exception e) {
			// log exception first, then return internal server error
			logger.error("Exception when create project - ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * @param projectId
	 * @param project
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{projectId}")
	public ResponseEntity<Project> save(@PathVariable final Long projectId, @RequestBody final Project project)
			throws InterruptedException {

		Project existProject = projectService.findById(projectId);
		if (existProject != null) {
			if (project != null) {
				BeanUtils.copyProperties(project, existProject,
						new String[] { "id", "createdAt", "createdBy", "updatedAt", "commentCount" });
			}
			Project savedProject = projectService.save(existProject);
			// The DB updated the update time stamp to CURRENT_TIMESTAMP,
			// the field updatedAt is not accurate at this moment
			return ResponseEntity.ok(savedProject);
		}
		return ResponseEntity.notFound().build();
	}

}
