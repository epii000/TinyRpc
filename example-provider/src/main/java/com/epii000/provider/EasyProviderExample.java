package com.epii000.provider;

import com.epii000.RpcApplication;
import com.epii000.registry.LocalRegistry;
import com.epii000.server.HttpServer;
import com.epii000.server.VertxHttpServer;
import com.epii000.service.UserService;

public class EasyProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
