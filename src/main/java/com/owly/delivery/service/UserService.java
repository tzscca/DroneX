package com.owly.delivery.service;


import com.owly.delivery.dao.UserDao;
import com.owly.delivery.entity.User;
import com.owly.delivery.entity.requestBody.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void signUp(User user) throws Exception {
        user.setEnabled(true);

        try{
            userDao.signUp(user);
        } catch (Exception ex){
            throw new Exception(ex);
        }
    }

    public User getUser(String email) {
        return userDao.getUser(email);
    }


    public User editUser(User newUser) throws Exception{
        User user = null;
        try {
            user = userDao.editUser(newUser);
        } catch (Exception ex){
            throw new Exception(ex);
        }
        return user;
    }

    public void changePassword(UserCredentials userCredentials) throws Exception {
        User user = null;
        try {
            user = getUser(userCredentials.getEmail());
            if(user == null){
                throw new Exception("No such user exists");
            }

            if(!user.getPassword().equals(userCredentials.getOldPassword())){
                throw new Exception("Password doesn't match");
            }

            user.setPassword(userCredentials.getNewPassword());
            userDao.editUser(user);

        } catch (Exception ex){
            throw new Exception(ex);
        }
    }
}

