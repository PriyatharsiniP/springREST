package com.dharshini.employeeapp.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dharshini.employeeapp.entity.Employee;
import com.dharshini.employeeapp.feignclient.AddressClient;
import com.dharshini.employeeapp.repository.EmployeeRepository;
import com.dharshini.employeeapp.response.AddressResponse;
import com.dharshini.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	private AddressResponse addressResponse = new AddressResponse();

	//@Autowired
	//private RestTemplate restTemplate;

	/*
	 * @Autowired private WebClient webClient;
	 */
	
	//Using Feign client to communicate between Microservice.
	@Autowired
	private AddressClient addressClient;
	/*
	 * Lets not use this, because when EmployeeService constructor is loaded during the
	 * class load itself, the value of addressBaseUrl will be null, since this
	 * variable will not be initialized at the class loading itself. So instead of
	 * using it here, we can pass this entirely in the constructor itself.
	 */
	//@Value("${addressservice.base.url}")
	//private String addressBaseUrl;

	//Another way of creating bean for RestTemplate without using @Autowired and creating @Bean in config file.
	//using constructor DI.
	/*
	 * public EmployeeService(@Value("${addressservice.base.url}") String
	 * addressBaseUrl, RestTemplateBuilder restTemplateBuilder) { this.restTemplate
	 * = restTemplateBuilder .rootUri(addressBaseUrl) .build(); }
	 */

	public EmployeeResponse getEmpDetails(int id) {

		Employee employee = employeeRepository.findById(id).get();

		EmployeeResponse employeeResponse = modelMapper.map(employee,EmployeeResponse.class);
		
		//------------------------------------------------------------------------------------
		/*
		 * first way of calling an API using REST client and getting the addressResponse
		 * data.
		 */
		//addressResponse = basicWayOfCallingOtherAPIUsingRestTemplate(id);
		
		//------------------------------------------------------------------------------------
		/*
		 * second way of calling an API using REST client and getting the
		 * addressResponse data.
		 */
		//addressResponse = updatedWayOfCallingOtherAPIUsingRestTemplate(id);
		
		//------------------------------------------------------------------------------------
		/*
		 * Third way of calling an API using REST client and getting the addressResponse
		 * data when we use RestTemplateBuilder the base url is already been given in
		 * constructor DI.
		 */
		//addressResponse = callingOtherApiByHavingBaseurlInConstructorDI(id);
		
		//------------------------------------------------------------------------------------
		/* calling using Webclient */
		/* addressResponse = callingOtherApiUsingWebclient(id); */

		//------------------------------------------------------------------------------------
		/* calling using FeignClient */
		//addressResponse = callingOtherApiUsingFeignclient(id);
		
		//------------------------------------------------------------------------------------
		/*
		 * Calling another microservice without directly giving the url and with client
		 * side load balancing using rest template (right way)
		 */
		//addressResponse = callingOtherApiUsingLoadBalancedRestTemplate(id);
		
		//------------------------------------------------------------------------------------
		/*
		 * Calling another microservice without directly giving the url and with client
		 * side load balancing using Feign client (right way)
		 */
		addressResponse = callingOtherApiUsingLoadBalancedFeignClient(id);
		
		employeeResponse.setAddressResponse(addressResponse);
		return employeeResponse;
	}
	//-----------------------------------------------------------------------------
	/*
	 * Basic way to call other service(API) from our service using RestTemplate by
	 * hard coding the url But this is bad practice- consider just for understanding.
	 */
	/*
	 * private AddressResponse basicWayOfCallingOtherAPIUsingRestTemplate(int id){
	 * addressResponse = restTemplate.getForObject(
	 * "http://localhost:8081/address-api/api/address/{id}",AddressResponse.class,
	 * id); return addressResponse; }
	 */
	
	//-----------------------------------------------------------------------------
	/*
	 * This is a bit updated way to call other service from ours by having the base
	 * url in our property file and using it here.
	 */
	/*
	 * private AddressResponse updatedWayOfCallingOtherAPIUsingRestTemplate(int id){
	 * addressResponse =
	 * restTemplate.getForObject(addressBaseUrl+"/address/{id}",AddressResponse.
	 * class, id); return addressResponse; }
	 */
	
	//-----------------------------------------------------------------------------
	/*
	 * Third way of calling an API using REST client and getting the addressResponse
	 * data when we use RestTemplateBuilder the base url is already been given in
	 * constructor DI - 1.3 version
	 */
	/*
	 * private AddressResponse callingOtherApiByHavingBaseurlInConstructorDI(int id)
	 * { addressResponse =
	 * restTemplate.getForObject("/address/{id}",AddressResponse.class, id); return
	 * addressResponse;
	 * 
	 * }
	 */
	
	//-----------------------------------------------------------------------------
	/*
	 * This method shows how to call other service or external API from our service
	 * using the spring web-flux's web client.
	 */
	/*
	 * private AddressResponse callingOtherApiUsingWebclient(int id){
	 * addressResponse = webClient.get() .uri("/address/"+id) .retrieve()
	 * .bodyToMono(AddressResponse.class) .block(); return addressResponse; }
	 */
	
	//-----------------------------------------------------------------------------
	/* Communicating between microservices using Feign client. */
	/*
	 * private AddressResponse callingOtherApiUsingFeignclient(int id){
	 * ResponseEntity<AddressResponse> addressResponseEntity =
	 * addressClient.getAddressByEmployeeId(id); return
	 * addressResponseEntity.getBody(); }
	 */
	
	//-----------------------------------------------------------------------------
	/*
	 * Communicating with other microservice without hardcoding the url and with
	 * client-side load balancer using rest template (correct way)
	 */
	/*
	 * private AddressResponse callingOtherApiUsingLoadBalancedRestTemplate(int id)
	 * { return restTemplate.getForObject(
	 * "http://address-service/address-app/api/address/{id}",AddressResponse.class,
	 * id); }
	 */
	
	//-----------------------------------------------------------------------------
	/*
	 * Communicating with other microservice without hardcoding the url and with
	 * client-side load balancer using Feign client (correct way)
	 */
	private AddressResponse callingOtherApiUsingLoadBalancedFeignClient(int id) {
		ResponseEntity<AddressResponse> addressResponseEntity =  addressClient.getAddressByEmployeeId(id);
		return addressResponseEntity.getBody();
	}
	
	/* Method to get all the employee details including address response */ 
	public List<EmployeeResponse> getAllEmployees(){
		List<Employee> empList =  employeeRepository.findAll();
		List<EmployeeResponse> empResponseList = Arrays.asList(modelMapper.map(empList,EmployeeResponse[].class));
		List<AddressResponse> addressResponseList = addressClient.getAllAddresses().getBody();
		for(EmployeeResponse empRes: empResponseList) {
			for(AddressResponse addrRes: addressResponseList) {
				if(empRes.getId()==addrRes.getEmployee_id()) {
					empRes.setAddressResponse(addrRes);
				}
			}
		}
		return empResponseList;
	}
	
	/* Method to create new employee details. */
	public EmployeeResponse  createEmployeeDetails(EmployeeResponse employeeResponse) {
		AddressResponse addressResponse = employeeResponse.getAddressResponse();
		addressResponse.setId(employeeResponse.getAddressResponse().getId());
		addressResponse.setEmployee_id(employeeResponse.getId());
		ResponseEntity<AddressResponse> savedAddressResponse = addressClient.createAddress(addressResponse);
		employeeResponse.setAddressResponse(savedAddressResponse.getBody());
		Employee employee = modelMapper.map(employeeResponse,Employee.class);
		employeeRepository.save(employee);
		EmployeeResponse savedEmployeeResponse = modelMapper.map(employee,EmployeeResponse.class);
		return savedEmployeeResponse;
	}

}
