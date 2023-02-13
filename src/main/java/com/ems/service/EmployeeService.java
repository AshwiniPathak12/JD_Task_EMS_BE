	package com.ems.service;

import java.util.List;

import com.ems.exception.ResourceNotFoundException;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;
import org.hibernate.annotations.Comment;
import org.hibernate.type.SortedMapType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class EmployeeService  {

	@Autowired
	private EmployeeRepository employeeRepository;

	// save employee
	public Employee addEmployee(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		return savedEmployee;
	}

	// get all employee
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();

	}

	// get employee by id
	public Employee getEmployeeById(int Employee_ID) {
		// return employeeRepository.getById(Employee_ID);
		return employeeRepository.findById(Employee_ID)
				.orElseThrow(() -> new ResourceNotFoundException("This Employee Id Does Not Exists " + Employee_ID));
	}

	// delete employee
	public void delete(int Employee_ID) {
		employeeRepository.deleteById(Employee_ID);
	}

	// update employee
	Employee updateEmployee(Employee employee, int Employee_ID) {
		return employeeRepository.save(employee);

	}

	// search employee on the basis of reporting manager
	public List<Employee> searchemployees(String query) {
		List<Employee> employees = employeeRepository.searchemployees(query);
		return employees;
	}

	// Pagination and Sorting on the basis of role
	public List<Employee> getAllEmployees(int pageno, int count) {
		Pageable pagable = PageRequest.of(pageno, count, Sort.by("role"));
		return employeeRepository.findAll(pagable).get().toList();

	}

}
