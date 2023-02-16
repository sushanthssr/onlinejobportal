package com.stackroute.userservices.service;

import java.util.List;

import com.stackroute.userservices.exceptions.JobAlreadyExistsException;
import com.stackroute.userservices.exceptions.JobNotFoundException;
import com.stackroute.userservices.model.Job;
import com.stackroute.userservices.model.User;

public interface WishlistService {
    public User saveJobToWishlist(Job job, String userName) throws JobAlreadyExistsException;

    public User deleteJobFromWishlist(String trackId, String userName) throws JobNotFoundException;

    public List<Job> getJobList(String userName) throws Exception;

}
