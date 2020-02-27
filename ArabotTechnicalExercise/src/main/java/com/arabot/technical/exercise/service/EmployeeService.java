package com.arabot.technical.exercise.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.arabot.technical.exercise.entity.Employee;
import com.arabot.technical.exercise.model.request.EmployeeRequest;
import com.arabot.technical.exercise.model.request.EmployeeSearchRequest;
import com.arabot.technical.exercise.model.response.EmployeeSalaryStaticsResponse;

public interface EmployeeService {

	Employee save(Employee employee);

	Employee create(EmployeeRequest employeeCreationRequest);

	Employee update(int id, EmployeeRequest employeeRequest);

	void delete(int id);

	Employee getEmployee(int id);

	List<Employee> searchEmployees(EmployeeSearchRequest employeeSearchRequest);

	Page<Employee> getAllEmployees(Pageable pageable);

	void raiseSalary(int id);

	void uploadCv(int id, MultipartFile file) throws IOException;

	List<Employee> topPaidEmployees();

	EmployeeSalaryStaticsResponse salaryStatic();

}
