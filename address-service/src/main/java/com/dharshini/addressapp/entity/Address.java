package com.dharshini.addressapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	//id, lane1, lane2, state, zip, employee_id
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="lane1")
	private String lane1;
	
	@Column(name="lane2")
	private String lane2;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private int zip;
	
	@Column(name="employee_id") 
	private int employee_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLane1() {
		return lane1;
	}

	public void setLane1(String lane1) {
		this.lane1 = lane1;
	}

	public String getLane2() {
		return lane2;
	}

	public void setLane2(String lane2) {
		this.lane2 = lane2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getEmployee_id() { 
		return employee_id; 
	}
	  
	public void setEmployee_id(int employee_id) { 
		this.employee_id = employee_id;
	}
	 

}
