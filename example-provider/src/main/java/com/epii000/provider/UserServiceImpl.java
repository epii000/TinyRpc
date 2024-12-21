package com.epii000.provider;

import com.epii000.model.User;
import com.epii000.service.UserService;

public class UserServiceImpl implements UserService {


    public User getUser(User user) {
        System.out.println("User " + user.getUsername());
        return user;
    }
}
