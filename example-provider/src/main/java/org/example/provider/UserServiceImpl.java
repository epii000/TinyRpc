package org.example.provider;

import org.example.model.User;
import org.example.service.UserService;

public class UserServiceImpl implements UserService {


    public User getUser(User user) {
        System.out.println("User " + user.getUsername());
        return user;
    }
}
