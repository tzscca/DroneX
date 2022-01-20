package com.owly.delivery.controller;

import com.owly.delivery.entity.User;
import com.owly.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@RequestBody User user, HttpServletResponse response) {
        System.out.println("After conversion");
        try{
            userService.signUp(user);
        } catch (Exception ex){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        response.setStatus(HttpStatus.CREATED.value());
    }
}

