package com.dharshini.customerapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dharshini.customerapp.response.CustomerResponse;
import com.dharshini.customerapp.service.CustomerService;

@RestController
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers/getAll")
	public ResponseEntity<List<CustomerResponse>> getAllCustomerData(){
		logger.info("Getting all the customer data");
		List<CustomerResponse> customerResponseList = customerService.getAllCustomerData();
		return ResponseEntity.status(HttpStatus.OK).body(customerResponseList);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerResponse> getCustomerDataById(@PathVariable("id") int id) {
		logger.info("Getting the customer data based on the given Id");
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerDataById(id));
	}
	
	@PostMapping("/customers/add")
	public ResponseEntity<CustomerResponse> addCustomerData(@RequestBody CustomerResponse customerResponse) {
		logger.info("Adding the customer data");
		CustomerResponse savedCustomerResponse = customerService.addCustomerData(customerResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerResponse);
	}
	
	@PutMapping("/customers/update/{id}")
	public ResponseEntity<CustomerResponse> updateCustomerData(@PathVariable("id") int id, @RequestBody CustomerResponse 
			customerResponse) {
		logger.info("Updating the customer data");
		customerService.updateCustomerData(id,customerResponse);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/customers/delete/{id}")
	public ResponseEntity<CustomerResponse> deleteCustomerDataById(@PathVariable("id") int id){
		logger.info("Deleting the customer data");
		customerService.deleteCustomerDataById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
