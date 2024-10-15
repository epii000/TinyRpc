package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.config.RegistryConfig;
import org.example.config.RpcConfig;
import org.example.constant.RpcConstant;
import org.example.registry.Registry;
import org.example.registry.RegistryFactory;
import org.example.utils.ConfigUtils;

@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config: {}", newRpcConfig.toString());

        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config: {}", registryConfig);

        // 创建并注册 Shutdown Hook， JVM 退出时并执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 提供获取单例对象的方法getRpcConfig()
     * 这个方法用于获取RpcConfig的单例对象。首先进行第一次检查，如果rpcConfig不为空，直接返回该对象。
     * 这一步是为了在大多数情况下避免进入同步块，提高性能。
     * 如果rpcConfig为空，进入同步块synchronized (RpcApplication.class)。在同步块内进行第二次检查，再次判断rpcConfig是否为空。
     * 这是因为可能有多个线程同时到达第一次检查，发现rpcConfig为空，但只有一个线程能够进入同步块。在同步块内进行第二次检查可以避免重复初始化。
     * 如果第二次检查时rpcConfig仍然为空，调用init方法进行初始化，然后返回rpcConfig对象。
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
