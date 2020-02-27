package com.arabot.technical.exercise.model.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("Employee Salary Statics Response")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryStaticsResponse {

	private double min;
	
	private double avarage;
	
	private double standardDeviation;
}
