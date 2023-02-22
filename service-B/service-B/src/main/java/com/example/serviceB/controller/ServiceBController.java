package com.example.serviceB.controller;

import com.example.serviceB.entity.Data;
import com.example.serviceB.service.ServiceBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceBController {

    @RequestMapping(value={"/data"},method= RequestMethod.GET)
    public Data getData(){
        Data data = new Data("Java Practice","1");
        return data;
    }

}
