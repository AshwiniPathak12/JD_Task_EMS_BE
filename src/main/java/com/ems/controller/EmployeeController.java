package com.ems.controller;

import java.util.List;

//import com.ems.dto.AuthRequest;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
//import com.ems.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;
	
//	@Autowired
	//private JwtService jwtService;

	// Save Employee
	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee employeeSaved = employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}

	// Fetch All Employee
	@GetMapping("/findAllEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> listEmp = employeeService.getAllEmployee();
		return new ResponseEntity<List<Employee>>(listEmp, HttpStatus.OK);
	}

	// Get Employee By Id
	@GetMapping("/getEmployeeById/{Employee_ID}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("Employee_ID") int Employee_ID) {
		Employee empById = employeeService.getEmployeeById(Employee_ID);
		return new ResponseEntity<Employee>(empById, HttpStatus.OK);
	}

	// update employee
	@PutMapping("/updateEmployee/{empid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("empid") Integer Employee_ID,
			@RequestBody Employee employee) {

		Employee employee1 = employeeService.getEmployeeById(Employee_ID);// findbyid

		employee1.setEmployee_Name(employee.getEmployee_Name());
		employee1.setDOJ(employee.getDOJ());
		employee1.setTotal_Experience(employee.getTotal_Experience());
		employee1.setReporting_Manager(employee.getReporting_Manager());
		employee1.setRole(employee.getRole());

		Employee updateEmployee = employeeService.addEmployee(employee1);
		return ResponseEntity.ok().body(updateEmployee);
	}

	// Delete employee
	@DeleteMapping("/deleteEmployee/{empid}")
	private String deleteBook(@PathVariable("empid") int Employee_ID) {
		employeeService.delete(Employee_ID);
		return "Deleted Successfully....!!!!";
	}

	// http://localhost:8087/search?query=y
	// search employee on the basis of reporting manager
	@GetMapping("/search")
	public ResponseEntity<List<Employee>> searchemployees(@RequestParam("query") String query) {
		return ResponseEntity.ok(employeeService.searchemployees(query));
	}

	// Pagination and Sorting on the basis of role
	@GetMapping("/getByName/{pageno}/{count}")
	public List<Employee> getAllEmployeePaging(@PathVariable int pageno, @PathVariable int count) {
		return employeeService.getAllEmployees(pageno, count);
	}

	// login

	@PostMapping("/login")
	public Status loginEmployee(@RequestBody Employee employee) {
		Employee EmployeeOld = employeeRepository.findbyEmailId(employee.getEmail());
		System.out.println(EmployeeOld);
		if (EmployeeOld != null) {
			if (EmployeeOld.getPassword().equals(employee.getPassword())) {
				return Status.SUCCESS;
			} else {
				return Status.FAILURE;
			}
		}
		return Status.FAILURE;
	}
	
	/*
	 * @PostMapping("/auth")// want to bypass so anyone can use it public String
	 * authAndGetToken( @RequestBody AuthRequest authRequest) { return
	 * jwtService.generateToken(authRequest.getUsername());
	 * 
	 * }
	 */
	/*
	 * public String loginEmployee(@RequestBody Employee employee) { if
	 * (employee.getEmail().equals("ashwini.lohar@yash.com") &&
	 * employee.getPassword().equals("ashwini@123")) return "admin login"; else
	 * return "admin login FAILURE"; }
	 */
}
