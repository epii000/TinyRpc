package org.example.fault.tolerant;


import org.example.model.RpcResponse;

import java.util.Map;

public interface TolerantStrategy {

    /**
     * 容错
     *
     * @param context
     * @param e
     * @return
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
