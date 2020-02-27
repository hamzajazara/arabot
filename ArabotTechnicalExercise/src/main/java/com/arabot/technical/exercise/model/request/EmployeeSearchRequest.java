package com.arabot.technical.exercise.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Employee Search Request")
@Setter
@Getter
public class EmployeeSearchRequest {

	private String name;

	private String department;
}