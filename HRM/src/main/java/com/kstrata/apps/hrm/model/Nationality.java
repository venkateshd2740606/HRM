package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nationality database table.
 * 
 */
@Entity
@Table(name="nationality")
@NamedQuery(name="Nationality.findAll", query="SELECT n FROM Nationality n")
public class Nationality implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5630906039468435480L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=100)
	private String name;

	public Nationality() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}