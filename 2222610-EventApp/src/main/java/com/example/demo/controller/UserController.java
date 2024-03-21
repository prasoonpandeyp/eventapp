package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenGenerator;
import com.example.demo.model.ResponseMessage;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/user-management")
public class UserController {

    //create a variable for userService
    //annotate with @Autowired to get the instance of UserService
    @Autowired
    private UserService userService;

    //create a variable for JwtTokenGenerator
    //annotate with @Autowired to get the instance of JwtTokenGenerator
    //private JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
    
    //make use of UserService to register User
    //annotate with @PostMapping to handle the post request
    //annotate with @RequestBody to get the user details from the request body
    @PostMapping("/register")
    public ResponseMessage registerUser(@RequestBody User user) {
        // Add your implementation here
        //call the saveUser method of userService
        return userService.saveUser(user);
    }

    //make use of UserService to get the user by emailId
    //annotate with @PostMapping to handle the post request
    //annotate with @PathVariable to get the emailId from the request body
    @GetMapping("/getUserByEmailId/{emailId}")
    public ResponseMessage getUserByEmailId(@PathVariable String emailId) {
        // Add your implementation here
        //call the getUserByEmailId method of userService
        return userService.getUserByEmailId(emailId);
    }
    
    @PostMapping("/login")
    public ResponseMessage loginUser(@RequestBody User user) {
        // Add your implementation here
        //call the saveUser method of userService
        User details= (User) userService.getUserByEmailId(user.getEmailid()).getResp();
        String tokeString = null;
        //create object of ResponseMessage
        ResponseMessage responseMessage = new ResponseMessage();
        if(details != null){
            tokeString =  jwtTokenGenerator.createJwtToken(user);
            //set values of ResponseMressage
            responseMessage.setRespMsg("User logged in successfully");
            responseMessage.setRespType("SUCCESS");
            responseMessage.setResp(tokeString);
        } else {
            //set values of ResponseMressage
            responseMessage.setRespMsg("User not found");
            responseMessage.setRespType("ERROR");
            responseMessage.setResp(null);
        }
        return responseMessage;
    }
    
}
