package com.epii000.config;

import lombok.Data;
import com.epii000.fault.retry.RetryStrategyKeys;
import com.epii000.fault.tolerant.TolerantStrategyKeys;
import com.epii000.loadbalancer.LoadBalancerKeys;
import com.epii000.serializer.SerializerKeys;

/**
 * RPC 框架配置
 */
@Data
public class RpcConfig {

    private String name = "tinyRpc";

    private String version = "1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;

    private boolean mock = false;

    private String serializer = SerializerKeys.JDK;

    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    private String retryStrategy = RetryStrategyKeys.NO;

    private String tolerantStrategy = TolerantStrategyKeys.FAIL_FAST;

    private RegistryConfig registryConfig = new RegistryConfig();
}
