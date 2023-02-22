package com.example.serviceA.controller;

import com.example.serviceA.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ServiceAController {
    @Autowired
    private RestTemplate restTemplate;
    private List<Date> dateString;
    @RequestMapping(value={"/report/{dateFrom}/{dateTo}"},method= RequestMethod.GET)
    public Data createReport(@PathVariable("dateFrom") @DateTimeFormat(pattern = "DD-mm-yyyy") @Valid Date dateFrom,
                             @PathVariable("dateTo") @DateTimeFormat(pattern="dd-MM-yyyy") @Valid Date dateTo){
        dateString = new ArrayList<>();
        dateString.add(dateFrom);
        dateString.add(dateTo);
        Data data = restTemplate.getForObject("http://localhost:8081/data",Data.class);
        data.setDateList(dateString);
        return data;
    }
}
