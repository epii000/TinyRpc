package org.example.consumer;

import org.example.config.RpcConfig;
import org.example.model.User;
import org.example.proxy.ServiceProxyFactory;
import org.example.service.UserService;
import org.example.utils.ConfigUtils;

import java.io.IOException;

public class ConsumerExample {

    public static void main(String[] args) throws IOException {
//        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
//        System.out.println(rpc);

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
