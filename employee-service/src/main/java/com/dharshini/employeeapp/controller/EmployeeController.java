package com.dharshini.employeeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dharshini.employeeapp.response.EmployeeResponse;
import com.dharshini.employeeapp.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
		//database call-> fetch employee data by id
		EmployeeResponse employeeResponse = employeeService.getEmpDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
		List<EmployeeResponse> empResponseList = employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(empResponseList);
	}

	@PostMapping("/employees/add")
	public ResponseEntity<EmployeeResponse> createEmployeeDetails(@RequestBody EmployeeResponse employeeResponse){
		EmployeeResponse savedEmpResponse = employeeService.createEmployeeDetails(employeeResponse);
		//return ResponseEntity.status(HttpStatus.CREATED).body(savedEmpResponse);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	

}
