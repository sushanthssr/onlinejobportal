package com.stackroute.userservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservices.exceptions.JobAlreadyExistsException;
import com.stackroute.userservices.exceptions.JobNotFoundException;
import com.stackroute.userservices.model.Job;
import com.stackroute.userservices.model.User;
import com.stackroute.userservices.repository.JobRepository;
import com.stackroute.userservices.repository.WishlistRepository;

@Service
public class WishlistImpl implements WishlistService {
    @Autowired
    private WishlistRepository watchListRepository;
    
    @Autowired
    private JobRepository jobRepository;

    @Override
    public User saveJobToWishlist(Job job, String userName) throws JobAlreadyExistsException {
        User user1 = watchListRepository.findByUserName(userName);
        // User user1 = user2.get();
        List<Job> JobList1 = user1.getJobList();
        if (JobList1 != null) {
            for (Job p : JobList1) {

                if (p.getId().equals(job.getId())) {
                    throw new JobAlreadyExistsException();
                }
            }

            JobList1.add(job);
            user1.setJobList(JobList1);
            jobRepository.save(job);
            watchListRepository.save(user1);
        }
        return user1;

    }

    @Override
    public User deleteJobFromWishlist(String id, String userName) throws JobNotFoundException {
        User user2 = watchListRepository.findByUserName(userName);
        // User user1 = user2.get();
        int indexnum = 0;
        List<Job> JobList1 = user2.getJobList();
        boolean status = false;
        if (JobList1!= null && JobList1.size() > 0) {
            for (Job t : JobList1) {
                indexnum++;
                if (t.getId().equals(id)) {
                    status = true;
                    JobList1.remove(indexnum - 1);
                    user2.setJobList(JobList1);
                    watchListRepository.save(user2);
                    break;
                }
            }
            if (status == false) {
                throw new JobNotFoundException();
            }
        }
        return user2;
    }

    @Override
    public List<Job> getJobList(String userName) throws Exception {
        User user1 = watchListRepository.findByUserName(userName);
        // User user2 = user1.get();
        return user1.getJobList();
    }

    
}
