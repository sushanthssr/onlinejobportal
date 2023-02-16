package com.stackroute.userservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/job")
    // public ResponseEntity<String> getJob(@RequestParam(name="page",required =
    // true) int page,@RequestParam(name="company", required = false) String
    // company,@RequestParam(name="category", required = false) String
    // category,@RequestParam(name="level",required = false) String level,
    // @RequestParam(name="location",required = false) String location){
    public ResponseEntity<String> getJob(@RequestParam(name = "page", required = true) int page,
            @RequestParam(name = "category", required = false) String category) {
        String queryappend = "";
        /*
         * if(!StringUtils.isEmpty(company)){
         * queryappend = queryappend+"&company="+company;
         * }
         * if(!StringUtils.isEmpty(level)){
         * queryappend = queryappend+"&level="+level;
         * }
         */
        if (!StringUtils.isEmpty(category)) {
            queryappend = queryappend + "&category=" + category;
        }
        /*
         * if(!StringUtils.isEmpty(location)){
         * queryappend = queryappend+"&location="+location;
         * }
         */
        System.out.println(queryappend);

        return ResponseEntity.ok(restTemplate
                .getForEntity("https://www.themuse.com/api/public/jobs?page=" + page + "" + queryappend, String.class)
                .getBody());
    }
}
