package com.dharshini.employeeapp.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dharshini.employeeapp.response.AddressResponse;

//Proxy given by spring which implicitly gives implementations for the methods inside it
@FeignClient(name="address-service",path="/address-app/api")
public interface AddressClient {

	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId);
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressResponse>> getAllAddresses();
	
	@PostMapping("/address/add")
	public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressResponse addressResponse);
}
