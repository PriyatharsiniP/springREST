package com.dharshini.employeeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dharshini.employeeapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	

}
