package org.example.consumer;

import org.example.bootstrap.ConsumerBootstrap;
import org.example.model.User;
import org.example.proxy.ServiceProxyFactory;
import org.example.service.UserService;

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
