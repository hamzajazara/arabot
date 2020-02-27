package com.arabot.technical.exercise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arabot.technical.exercise.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByNameOrDepartmentName(String name, String departmentName);
	
	@Query("SELECT em, max(em.salary) FROM Employee em Group by em.id")
	List<Employee> topPaid();
	
	@Query("SELECT min(salary), avg(salary), sqrt((sum(salary*salary)/count(salary)) - (avg(salary) * avg(salary))) FROM Employee")
	List<Object[]> salaryStatic();
}
