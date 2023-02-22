package com.example.serviceB.repository;

import com.example.serviceB.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceBRepo extends JpaRepository<Data,String> {
    @Override
    List<Data> findAll();
}
