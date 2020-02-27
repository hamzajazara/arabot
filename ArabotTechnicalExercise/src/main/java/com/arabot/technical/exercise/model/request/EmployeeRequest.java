package com.arabot.technical.exercise.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Employee Request")
@Setter
@Getter
public class EmployeeRequest {

	@NotBlank
	private String name;

	@PositiveOrZero
	private double salary;

	@NotBlank
	private String departmentName;
}
