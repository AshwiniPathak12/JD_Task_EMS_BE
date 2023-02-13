package com.ems.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import com.ems.model.Employee;


import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;



@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT u FROM Employee u WHERE u.Reporting_Manager LIKE CONCAT('%',:query, '%')")
	List<Employee> searchemployees(String query);
	
	@Query("From Employee a where a.email=:email")
	Employee findbyEmailId(@Param("email") String email);

	

	
	
	
}
