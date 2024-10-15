package org.example.config;

import lombok.Data;
import org.example.serializer.SerializerKeys;

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

    private RegistryConfig registryConfig = new RegistryConfig();
}
