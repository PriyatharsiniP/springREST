package com.example.serviceB.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data")
public class Data {
    @Column(name="name")
    private String name;
    @Id
    @Column(name="id")
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Data() {
    }

    public Data(String name, String id) {
        this.name = name;
        this.id = id;
    }

}
