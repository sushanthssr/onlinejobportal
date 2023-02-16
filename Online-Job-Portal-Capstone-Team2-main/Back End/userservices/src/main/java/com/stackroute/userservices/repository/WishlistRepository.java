package com.stackroute.userservices.repository;

import org.springframework.data.repository.CrudRepository;

import com.stackroute.userservices.model.User;

public interface WishlistRepository extends CrudRepository<User, String> {
    public User findByUserName(String userName);

}
