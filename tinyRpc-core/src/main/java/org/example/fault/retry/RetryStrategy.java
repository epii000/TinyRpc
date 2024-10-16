package org.example.fault.retry;

import org.example.model.RpcRequest;
import org.example.model.RpcResponse;

import java.util.concurrent.Callable;

public interface RetryStrategy {

    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
