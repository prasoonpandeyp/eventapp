package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ResponseMessage;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@Service
public class UserService {

    //autowire the UserRepository
    @Autowired
    private UserRepository userRepository;

    //create a method to get the user by emailId
    public ResponseMessage getUserByEmailId(String emailId) {
        // Add your implementation here
        //return the user by emailId

        //create a response message object
        ResponseMessage responseMessage = new ResponseMessage();
        //get the user by emailId
        User user = userRepository.findByEmailId(emailId);
        //check if the user is null
        if (user == null) {
            //set the response message
            responseMessage.setRespMsg("User not found");
            responseMessage.setRespType("ERROR");
            responseMessage.setResp(null);
        } else {
            //set the response message
            responseMessage.setRespMsg(emailId + " found");
            responseMessage.setRespType("SUCESS");
            responseMessage.setResp(user);
        }
        return responseMessage;
    }

    //create a method to save the user
    public ResponseMessage saveUser(User user) {
        // Add your implementation here
        //save the user
        
        //create a response message object
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            if (user != null) {
                //call userRepository.save method to save the user
                User updatedUser = userRepository.save(user);
                //set the response message
                responseMessage.setRespMsg("User saved successfully");
                responseMessage.setRespType("SUCCESS");
                responseMessage.setResp(updatedUser);
            } else {
                //set the response message
                responseMessage.setRespMsg("User not saved");
                responseMessage.setRespType("ERROR");
                responseMessage.setResp(null);            
            }
        } catch (Exception e) {
            //handle the exception
        }
        //return a ResponseMessage object
        return new ResponseMessage();
    }

}
