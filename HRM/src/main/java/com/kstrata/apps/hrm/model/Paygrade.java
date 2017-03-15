package com.kstrata.apps.hrm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the paygrades database table.
 * 
 */
@Entity
@Table(name="paygrades")
@NamedQuery(name="Paygrade.findAll", query="SELECT p FROM Paygrade p")
public class Paygrade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4100894394112569187L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=3)
	private String currency;

	@Column(name="max_salary", precision=10, scale=2)
	private Double maxSalary;

	@Column(name="min_salary", precision=10, scale=2)
	private BigDecimal minSalary;

	@Column(length=100)
	private String name;

	public Paygrade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getMaxSalary() {
		return this.maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public BigDecimal getMinSalary() {
		return this.minSalary;
	}

	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}