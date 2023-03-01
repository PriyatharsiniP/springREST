package com.dharshini.addressapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dharshini.addressapp.response.AddressResponse;
import com.dharshini.addressapp.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId){
		AddressResponse addressResponse = addressService.getAddressByEmployeeId(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressResponse>> getAllAddresses(){
		List<AddressResponse> addressResponseList = addressService.getAllAddresses();
		return ResponseEntity.status(HttpStatus.OK).body(addressResponseList);
	}
	
	@PostMapping("/address/add")
	public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressResponse addressResponse) throws Exception{
		ResponseEntity<AddressResponse> savedAddressResponse = addressService.createAddress(addressResponse);
		return savedAddressResponse;
	}
}
