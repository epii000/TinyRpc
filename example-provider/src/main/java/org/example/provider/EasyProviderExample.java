package org.example.provider;

import org.example.RpcApplication;
import org.example.registry.LocalRegistry;
import org.example.server.HttpServer;
import org.example.server.VertxHttpServer;
import org.example.service.UserService;

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
