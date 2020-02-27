package com.arabot.technical.exercise.model.response;

import com.arabot.technical.exercise.entity.Employee;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Employee Response")
@Setter
@Getter
public class EmployeeResponse {

	private String name;

	private double salary;

	public EmployeeResponse(Employee employee) {
		this.name = employee.getName();
		this.salary = employee.getSalary();
	}
}
