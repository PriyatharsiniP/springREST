package com.dharshini.employeeapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmployeeConfig {
	
	/*
	 * This base url value is set in application.properties file and we are fetching
	 * it using @Value like below
	 */
	@Value("${addressservice.base.url}")
	private String addressBaseUrl;
	
	/*
	 * This is a third party library with which we can map our entity to this
	 * wrapper class rather than directly using it
	 */
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	/*
	 * @Bean public RestTemplate getRestTemplate() { return new RestTemplate(); }
	 */
	
	/*
	 * @Bean public WebClient getWebClient() { return
	 * WebClient.builder().baseUrl(addressBaseUrl).build(); }
	 */
	
	/*
	 * Rest template using client-side load balancer and without hard coding the url
	 */
	/*
	 * @Bean
	 * 
	 * @LoadBalanced public RestTemplate getRestTemplate() { return new
	 * RestTemplate(); }
	 */
	
}
