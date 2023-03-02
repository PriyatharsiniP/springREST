package com.dharshini.customerapp.controller;

import java.util.List;

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
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers/getAll")
	public ResponseEntity<List<CustomerResponse>> getAllCustomerData(){
		List<CustomerResponse> customerResponseList = customerService.getAllCustomerData();
		return ResponseEntity.status(HttpStatus.OK).body(customerResponseList);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerResponse> getCustomerDataById(@PathVariable("id") int id) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerDataById(id));
	}
	
	@PostMapping("/customers/add")
	public ResponseEntity<CustomerResponse> addCustomerData(@RequestBody CustomerResponse customerResponse) {
		CustomerResponse savedCustomerResponse = customerService.addCustomerData(customerResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerResponse);
	}
	
	@PutMapping("/customers/update/{id}")
	public ResponseEntity<CustomerResponse> updateCustomerData(@PathVariable("id") int id, @RequestBody CustomerResponse 
			customerResponse) {
		customerService.updateCustomerData(id,customerResponse);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/customers/delete/{id}")
	public ResponseEntity<CustomerResponse> deleteCustomerDataById(@PathVariable("id") int id){
		customerService.deleteCustomerDataById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
