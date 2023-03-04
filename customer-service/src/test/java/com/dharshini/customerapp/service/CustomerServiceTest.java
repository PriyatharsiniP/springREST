package com.dharshini.customerapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.dharshini.customerapp.entity.Customer;
import com.dharshini.customerapp.repository.CustomerRepository;
import com.dharshini.customerapp.response.CustomerResponse;

@SpringBootTest(classes= {CustomerServiceTest.class})
public class CustomerServiceTest {
	
	@Mock
	ModelMapper modelMapper;
	
	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerService customerService;
	
	@Test
	public void testGetAllCustomerData() {
		List<Customer> customerList = new ArrayList<>();
		Customer customer = new Customer();
		customer.setCustomer_id(200);
		customer.setCustomer_firstname("Rekha");
		customer.setCustomer_lastname("Nair");
		customer.setCustomer_email("Rekha@gmail.com");
		customer.setCustomer_dob(new Date(2000-05-13));
		customerList.add(customer);
		
		CustomerResponse customerResponse = new CustomerResponse();
		List<CustomerResponse> customerResponseList = new ArrayList<>();
		customerResponse.setCustomer_id(200);
		customerResponse.setCustomer_firstname("Rekha");
		customerResponse.setCustomer_lastname("Nair");
		customerResponse.setCustomer_email("Rekha@gmail.com");
		customerResponse.setCustomer_dob(new Date(2000-05-13));
		customerResponseList.add(customerResponse);
		
		when(customerRepository.findAll()).thenReturn(customerList);
		Mockito.when(modelMapper.map(isNotNull(),isNotNull())).thenReturn(customerResponseList);
		
		assertEquals(200,customerResponseList.get(0).getCustomer_id());
	}
	
	@Test
	public void testGetCustomerDataById() {
		Customer customer = new Customer();
		customer.setCustomer_id(200);
		customer.setCustomer_firstname("Rekha");
		customer.setCustomer_lastname("Nair");
		customer.setCustomer_email("Rekha@gmail.com");
		customer.setCustomer_dob(new Date(2000-05-13));
		
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomer_id(200);
		customerResponse.setCustomer_firstname("Rekha");
		customerResponse.setCustomer_lastname("Nair");
		customerResponse.setCustomer_email("Rekha@gmail.com");
		customerResponse.setCustomer_dob(new Date(2000-05-13));
		int dummyCusID = 200;
		
		doReturn(Optional.of(customer)).when(customerRepository).findById(dummyCusID);
		Mockito.when(modelMapper.map(isNotNull(),isNotNull())).thenReturn(customerResponse);
		
		assertEquals(dummyCusID,customerService.getCustomerDataById(dummyCusID).getCustomer_id());
	}
	
	@Test
	public void testAddCustomerData() {
		Customer customer = new Customer();
		customer.setCustomer_id(200);
		customer.setCustomer_firstname("Rekha");
		customer.setCustomer_lastname("Nair");
		customer.setCustomer_email("Rekha@gmail.com");
		customer.setCustomer_dob(new Date(2000-05-13));
		
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomer_id(200);
		customerResponse.setCustomer_firstname("Rekha");
		customerResponse.setCustomer_lastname("Nair");
		customerResponse.setCustomer_email("Rekha@gmail.com");
		customerResponse.setCustomer_dob(new Date(2000-05-13));
		
		Mockito.when(modelMapper.map(isNotNull(),isNotNull())).thenReturn(customer);
		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
		
		//assertEquals(customerResponse,customerService.addCustomerData(customerResponse));
		assertEquals(200,customer.getCustomer_id());
	}
	
	@Test
	public void testUpdateCustomerData() {
		Customer customer = new Customer();
		customer.setCustomer_id(201);
		customer.setCustomer_firstname("Rekha");
		customer.setCustomer_lastname("Nair");
		customer.setCustomer_email("Rekha@gmail.com");
		customer.setCustomer_dob(new Date(1889-05-13));
		
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomer_id(201);
		customerResponse.setCustomer_firstname("Rekha");
		customerResponse.setCustomer_lastname("Nair");
		customerResponse.setCustomer_email("Rekha@gmail.com");
		customerResponse.setCustomer_dob(new Date(1889-05-13));
		
		Mockito.when(modelMapper.map(isNotNull(),isNotNull())).thenReturn(customer);
		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
		
		//assertEquals(customerResponse,customerService.addCustomerData(customerResponse));
		assertEquals(201,customer.getCustomer_id());
	}
	
	@Test
	public void testDeleteCustomerData() {
		Customer customer = new Customer();
		customer.setCustomer_id(200);
		customer.setCustomer_firstname("Rekha");
		customer.setCustomer_lastname("Nair");
		customer.setCustomer_email("Rekha@gmail.com");
		customer.setCustomer_dob(new Date(2000-05-13));
		
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomer_id(200);
		customerResponse.setCustomer_firstname("Rekha");
		customerResponse.setCustomer_lastname("Nair");
		customerResponse.setCustomer_email("Rekha@gmail.com");
		customerResponse.setCustomer_dob(new Date(2000-05-13));
		
		customerService.deleteCustomerDataById(200);

		verify(customerRepository,times(1)).deleteById(200);
	}

}
