package com.owly.delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.owly.delivery.entity.User;
import com.owly.delivery.entity.requestBody.UserCredentials;
import com.owly.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserInfoController {


    @Autowired
    private UserService userService;


    private final ObjectMapper objectMapper = new ObjectMapper();


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return userService.getUser(username);
    }


    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    @ResponseBody
    public User editUser(@RequestBody User newUser, HttpServletResponse response){
        User user = null;
        try{
            user = userService.editUser(newUser);
        } catch (Exception ex){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        if(user == null){
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return user;

    }


    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public void changePassword(@RequestBody UserCredentials user, HttpServletResponse response) throws Exception{
        try{
            userService.changePassword(user);
        } catch (Exception ex){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String, Object> data = new HashMap<>();
            data.put("message", ex.getMessage());
            response.getOutputStream()
                    .println(objectMapper.writeValueAsString(data));
            return;
        }

        response.setStatus(HttpStatus.CREATED.value());

    }

}
