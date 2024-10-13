package org.example.consumer;

import org.example.model.User;
import org.example.proxy.ServiceProxyFactory;
import org.example.service.UserService;
import org.example.proxy.ServiceProxyFactory;

import java.io.IOException;

public class ConsumerExample {
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
