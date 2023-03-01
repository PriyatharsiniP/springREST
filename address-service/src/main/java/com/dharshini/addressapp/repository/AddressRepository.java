package com.dharshini.addressapp.repository;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dharshini.addressapp.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer>{
	
	@Query(value="select id, lane1, lane2, state, zip, employee_id from address where employee_id = :employeeId", nativeQuery=true)
	public Address findAddressByEmployeeId(@PathParam("employeeId") int employeeId);
	
}
