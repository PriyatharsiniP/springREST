package com.example.serviceA.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class Data {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message="Id cannot be blank")
    private String id;
    private List<Date> dateList;
    public Data() {

    }
    public Data(String name, String id,List<Date> dateList) {
        this.name = name;
        this.id = id;
        this.dateList = dateList;
    }

    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }

    public List<Date> getDateList() {
        return dateList;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
}
