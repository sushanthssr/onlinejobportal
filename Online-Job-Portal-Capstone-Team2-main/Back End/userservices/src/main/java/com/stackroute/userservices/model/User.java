package com.stackroute.userservices.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique=true)
    private String userName;
    private String email;
    private String password;
    private String qualification;
    @ManyToMany
    private List<Job> JobList;

//    public void copyWithoutNotNull(User user){
//        if(StringUtils.isNotEmpty(user.getuserName())){
//        this.userName= user.userName;
//    }
//    if(StringUtils.isNotEmpty(user.getEmail())){
//        this.email= user.email;
//    }
//    if(StringUtils.isNotEmpty(user.getQualification())){
//        this.qualification= user.qualification;
//    }
//    
//    }

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", qualification=" + qualification + ", JobList=" + JobList + "]";
	}

	public List<Job> getJobList() {
		return JobList;
	}

	public void setJobList(List<Job> jobList) {
		JobList = jobList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public User() {
		super();
	}

	public User(String userName, List<Job> jobList) {
		super();
		this.userName = userName;
		JobList = jobList;
	}
    
}
