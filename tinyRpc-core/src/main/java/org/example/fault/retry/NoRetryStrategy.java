package org.example.fault.retry;

import lombok.extern.slf4j.Slf4j;
import org.example.model.RpcResponse;

import java.util.concurrent.Callable;

@Slf4j
public class NoRetryStrategy implements RetryStrategy {
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
