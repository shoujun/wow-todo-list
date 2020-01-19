/**
 * 
 */
package com.wownetwork.todolist.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//
//project
//
//CREATE TABLE `project` (
//		  `id` int(11) NOT NULL AUTO_INCREMENT,
//		  `name` varchar(256) NOT NULL,
//		  `sort_order` int(11) DEFAULT '1',
//		  `indent` int(11) DEFAULT '1',
//		  `comment_count` int(11) DEFAULT '0',
//		  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
//		  `created_by` varchar(64) DEFAULT NULL,
//		  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//		  `updated_by` varchar(64) DEFAULT NULL,
//		  PRIMARY KEY (`id`)
//		) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
//

/**
 * @author ShouJun
 *
 */
@Entity(name = "Project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends AbstractCommonEntity implements Serializable {

	/** Generated */
	private static final long serialVersionUID = -5822700444546147046L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
//	private Long id;

	// Has to be provided
	@Column(name = "name", nullable = false, length = 256)
	private String name;

	// Provides the default value to 1
	@Column(name = "sortOrder", nullable = true)
	private Integer order = 1;

	// Provides the default value to 1
	@Column(name = "indent", nullable = true)
	private Integer indent = 1;

	// Don't include in the INSERT statement, so DB default value will be used
	@Column(name = "commentCount", nullable = true, insertable = false)
	private Long commentCount;

	/** Default constructor */
	public Project() {
	}
//
//	/**
//	 * @return the id
//	 */
//	public Long getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Long id) {
//		this.id = id;
//	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return the indent
	 */
	public Integer getIndent() {
		return indent;
	}

	/**
	 * @param indent the indent to set
	 */
	public void setIndent(Integer indent) {
		this.indent = indent;
	}

	/**
	 * @return the commentCount
	 */
	public Long getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

}
