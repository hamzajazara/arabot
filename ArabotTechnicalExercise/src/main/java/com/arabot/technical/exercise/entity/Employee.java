package com.arabot.technical.exercise.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "salary")
	private double salary;

	@Column(name = "active")
	private boolean active;

	@Column(name = "cv")
	private byte[] cv;

	@Column(name = "department_name")
	private String departmentName;
}
