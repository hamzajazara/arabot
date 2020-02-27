package com.arabot.technical.exercise.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arabot.technical.exercise.entity.Employee;
import com.arabot.technical.exercise.model.request.EmployeeRequest;
import com.arabot.technical.exercise.model.request.EmployeeSearchRequest;
import com.arabot.technical.exercise.model.response.EmployeeSalaryStaticsResponse;
import com.arabot.technical.exercise.repository.EmployeeRepository;
import com.arabot.technical.exercise.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(int id) {
		return employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public Employee create(EmployeeRequest employeeRequest) {
		Employee employee = new Employee();
		employee.setName(employeeRequest.getName());
		employee.setSalary(employeeRequest.getSalary());
		employee.setDepartmentName(employeeRequest.getDepartmentName());
		return save(employee);
	}

	@Override
	public Employee update(int id, EmployeeRequest employeeRequest) {
		Employee employee = getEmployee(id);
		employee.setName(employeeRequest.getName());
		employee.setSalary(employeeRequest.getSalary());
		employee.setDepartmentName(employeeRequest.getDepartmentName());
		return save(employee);
	}

	@Override
	public void delete(int id) {
		Employee employee = getEmployee(id);
		employeeRepository.delete(employee);
	}

	@Override
	public List<Employee> searchEmployees(EmployeeSearchRequest employeeSearchRequest) {
		return employeeRepository.findByNameOrDepartmentName(employeeSearchRequest.getName(),
				employeeSearchRequest.getDepartment());
	}

	@Override
	public Page<Employee> getAllEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	@Override
	public void uploadCv(int id, MultipartFile file) throws IOException {
		Employee employee = getEmployee(id);
		employee.setCv(file.getBytes());
		save(employee);
	}

	@Override
	public void raiseSalary(int id) {
		Employee employee = getEmployee(id);
		double salary = employee.getSalary();
		employee.setSalary(salary + (salary / 0.1));
	}

	@Override
	public List<Employee> topPaidEmployees() {
		return employeeRepository.topPaid();
	}

	@Override
	public EmployeeSalaryStaticsResponse salaryStatic() {
		Object[] salaryStatic = employeeRepository.salaryStatic().get(0);
		return new EmployeeSalaryStaticsResponse((Double) salaryStatic[0], (Double) salaryStatic[1],
				(Double) salaryStatic[2]);
	}
}
