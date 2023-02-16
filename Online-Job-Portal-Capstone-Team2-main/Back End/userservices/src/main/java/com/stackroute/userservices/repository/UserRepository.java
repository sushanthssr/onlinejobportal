package com.stackroute.userservices.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.userservices.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);
}

