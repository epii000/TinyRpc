package com.epii000.fault.tolerant;

import com.epii000.model.RpcResponse;

import java.util.Map;

/**
 * 转移到其他服务节点
 */
public class FailOverTolerantStrategy implements TolerantStrategy {
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // todo
        return null;
    }
}
