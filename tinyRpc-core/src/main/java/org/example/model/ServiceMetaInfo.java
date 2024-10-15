package org.example.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.example.constant.RpcConstant;

/**
 * 服务元信息，注册信息
 */
@Data
public class ServiceMetaInfo {

    private String serviceName;

    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;

    private String serviceHost;

    private Integer servicePort;

    private String serviceGroup = "default";

    /**
     * 获取服务键名
     *
     * @return
     */
    public String getServiceKey() {
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务注册节点键名
     *
     * @return
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }
}
