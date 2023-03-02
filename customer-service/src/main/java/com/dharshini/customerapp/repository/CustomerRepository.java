package com.dharshini.customerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dharshini.customerapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}
