package com.epii000.config;

import lombok.Data;
import com.epii000.registry.RegistryKeys;

/**
 * RPC 框架注册中心配置
 */
@Data
public class RegistryConfig {

    private String registry = RegistryKeys.ETCD;

    private String address = "http://localhost:2380";

    private String username;

    private String password;

    private Long timeout = 10000L;
}
