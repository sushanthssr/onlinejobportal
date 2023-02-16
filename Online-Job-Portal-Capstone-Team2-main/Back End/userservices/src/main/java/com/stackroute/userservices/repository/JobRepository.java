package com.stackroute.userservices.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.userservices.model.Job;

public interface JobRepository extends JpaRepository<Job, String> {
}

