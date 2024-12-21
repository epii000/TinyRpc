package com.epii000.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();

        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并且处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动HTTP服务器并制动监听端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server started on port " + port);
            } else {
                System.out.println("Failed to start server on port " + result.cause());
            }
        });
    }
}
