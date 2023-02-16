package com.stackroute.userservices.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservices.dto.UserDto;
import com.stackroute.userservices.exceptions.UserAlreadyExistException;
import com.stackroute.userservices.exceptions.UserNotFoundException;
import com.stackroute.userservices.exceptions.UserServicesException;
import com.stackroute.userservices.model.User;
import com.stackroute.userservices.model.UserHelper;
import com.stackroute.userservices.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET })
@RequestMapping("/auth")
public class UserController {
    private UserService userService;
    private ResponseEntity<?> responseEntity;
    @Autowired
    private ModelMapper modelMapper;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        User user = modelMapper.map(userDto, User.class);
        User user1 = userService.register(user);
        UserDto userDto2 = modelMapper.map(user1, UserDto.class);
        responseEntity = new ResponseEntity<UserDto>(userDto2, HttpStatus.CREATED);

        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) throws UserNotFoundException {
        Map<String, String> map = new HashMap<String, String>();
        User user = modelMapper.map(userDto, User.class);
        String jwtToken = "";
        try {
            User user2 = userService.login(user.getuserName(), user.getPassword());
            if (user2.getuserName().equals(user.getuserName())) {
                jwtToken = getToken(user.getuserName(), user.getPassword());
                map.put("message", "login successfull");
                map.put("token", jwtToken);

            }
            responseEntity = new ResponseEntity<>(map, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }

        return responseEntity;
    }

    public String getToken(String userName, String password) throws UserServicesException {
        try {

            String jwtToken = Jwts.builder()
                    .setSubject(userName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 30000))
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

            return jwtToken;
        } catch (Exception e) {
            throw new UserServicesException();
        }

    }

    @PutMapping("/update/{userName}")
    public User UpadteProfile(@PathVariable("userName") String userName, @RequestBody User user)
            throws UserServicesException {
        return userService.updateProfile(user, userName);
    }

    @PutMapping("/changePassword")
    public String changePassword(@RequestBody UserHelper userHelper) throws UserServicesException {
        return userService.changePassword(userHelper);
    }

    @GetMapping("/get/{userName}")
    public User getProfile(@PathVariable("userName") String userName) throws UserServicesException {
        return userService.getProfile(userName);
    }

}
