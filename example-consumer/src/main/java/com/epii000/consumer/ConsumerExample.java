package com.epii000.consumer;

import com.epii000.bootstrap.ConsumerBootstrap;
import com.epii000.model.User;
import com.epii000.proxy.ServiceProxyFactory;
import com.epii000.service.UserService;

import java.io.IOException;

public class ConsumerExample {

    public static void main(String[] args) throws IOException {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setUsername("haha");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getUsername());
        } else {
            System.out.println("user not found");
        }
    }
}
