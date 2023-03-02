package com.dharshini.customerapp.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharshini.customerapp.entity.Customer;
import com.dharshini.customerapp.repository.CustomerRepository;
import com.dharshini.customerapp.response.CustomerResponse;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CustomerResponse> getAllCustomerData(){
		List<Customer> customerList = customerRepository.findAll();
		List<CustomerResponse> customerResponseList = Arrays.asList(modelMapper.map(customerList,CustomerResponse[].class));
		return customerResponseList;
	}
	
	public CustomerResponse getCustomerDataById(int id) {
		Customer customer = customerRepository.findById(id).get();
		CustomerResponse customerRepsonse = modelMapper.map(customer,CustomerResponse.class);
		return customerRepsonse;
	}
	
	public CustomerResponse addCustomerData(CustomerResponse customerResponse) {
		Customer customer = modelMapper.map(customerResponse,Customer.class);
		Customer savedCustomerData = customerRepository.save(customer);
		return modelMapper.map(savedCustomerData,CustomerResponse.class);
	}
	
	public CustomerResponse updateCustomerData(int id, CustomerResponse customerResponse) {
		Customer customer = modelMapper.map(customerResponse,Customer.class);
		customerRepository.save(customer);
		return modelMapper.map(customer,CustomerResponse.class);
	}
	
	public void deleteCustomerDataById(int id) {
		customerRepository.deleteById(id);
	}
}
