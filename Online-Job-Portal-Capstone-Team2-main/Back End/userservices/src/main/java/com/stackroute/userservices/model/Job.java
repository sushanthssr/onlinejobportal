package com.stackroute.userservices.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Job {
    public Job(String id, String name, String location, String role, String company, String landing_page) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.role = role;
		this.company = company;
		this.landing_page = landing_page;
	}

	@Id
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("role")
    private String role;

    @JsonProperty("company")
    private String company;

    @JsonProperty("landing_page")
    private String landing_page;

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", location=" + location + ", role=" + role + ", company=" + company
				+ ", landing_page=" + landing_page + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLanding_page() {
		return landing_page;
	}

	public void setLanding_page(String landing_page) {
		this.landing_page = landing_page;
	}

	public Job() {
		super();
	}
    
    
}
