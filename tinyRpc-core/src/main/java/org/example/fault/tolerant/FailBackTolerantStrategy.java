package org.example.fault.tolerant;

import lombok.extern.slf4j.Slf4j;
import org.example.model.RpcResponse;

import java.util.Map;

/**
 * 降级到其他服务
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // todo
        return null;
    }
}
