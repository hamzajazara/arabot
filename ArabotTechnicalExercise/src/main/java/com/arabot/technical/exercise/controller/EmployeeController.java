package com.arabot.technical.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arabot.technical.exercise.entity.Employee;
import com.arabot.technical.exercise.model.request.EmployeeRequest;
import com.arabot.technical.exercise.model.request.EmployeeSearchRequest;
import com.arabot.technical.exercise.model.request.UploadFileResponse;
import com.arabot.technical.exercise.model.response.EmployeeResponse;
import com.arabot.technical.exercise.model.response.EmployeeSalaryStaticsResponse;
import com.arabot.technical.exercise.service.EmployeeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/employee")
@Api(tags = "Employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/")
	public ResponseEntity<EmployeeResponse> create(EmployeeRequest employeeCreationRequest) {
		return new ResponseEntity<>(new EmployeeResponse(employeeService.create(employeeCreationRequest)),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> update(@PathVariable int id, EmployeeRequest employeeRequest) {
		return new ResponseEntity<>(new EmployeeResponse(employeeService.update(id, employeeRequest)),
				HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/")
	public ResponseEntity<?> delete(@PathVariable int id) {
		employeeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/search")
	public ResponseEntity<List<EmployeeResponse>> searchEmployees(EmployeeSearchRequest employeeSearchRequest) {
		List<Employee> searchEmployees = employeeService.searchEmployees(employeeSearchRequest);
		List<EmployeeResponse> employeeResponses = new ArrayList<>();
		searchEmployees.forEach(employee -> employeeResponses.add(new EmployeeResponse(employee)));
		return new ResponseEntity<>(employeeResponses, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(Pageable pageable) {
		Page<Employee> allEmployees = employeeService.getAllEmployees(pageable);
		List<EmployeeResponse> employeeResponses = new ArrayList<>();
		allEmployees.forEach(employee -> employeeResponses.add(new EmployeeResponse(employee)));
		return new ResponseEntity<>(new PageImpl<>(employeeResponses), HttpStatus.OK);
	}

	@PatchMapping("/{employeeId}/upload/cv")
	public ResponseEntity<UploadFileResponse> uploadCv(@PathVariable int employeeId,
			@RequestParam("file") MultipartFile file) throws IOException {
		employeeService.uploadCv(employeeId, file);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/raise-salary/{id}")
	public ResponseEntity<?> raiseSalary(@PathVariable int id) {
		employeeService.raiseSalary(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/top-paid")
	public ResponseEntity<List<EmployeeResponse>> getTopPaidEmployee() {
		List<Employee> topPaidEmployees = employeeService.topPaidEmployees();
		List<EmployeeResponse> employeeResponses = new ArrayList<>();
		topPaidEmployees.forEach(employee -> employeeResponses.add(new EmployeeResponse(employee)));
		return new ResponseEntity<>(employeeResponses, HttpStatus.OK);
	}

	@GetMapping("/salary/static")
	public ResponseEntity<EmployeeSalaryStaticsResponse> getSalaryStatic() {
		return new ResponseEntity<>(employeeService.salaryStatic(), HttpStatus.OK);
	}
}
