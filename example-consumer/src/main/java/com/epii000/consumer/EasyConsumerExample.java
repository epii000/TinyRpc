package com.epii000.consumer;

import com.epii000.model.User;
import com.epii000.proxy.ServiceProxyFactory;
import com.epii000.service.UserService;

import java.io.IOException;

public class EasyConsumerExample {
    public static void main(String[] args) throws IOException {

//        // 静态代理方式
//        UserService userService1 = new UserServiceProxy();

        // 动态代理方式
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setUsername("haha");

        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getUsername());
        } else {
            System.out.println("no such user");
        }
    }
}
