package com.epii000.fault.retry;

import lombok.extern.slf4j.Slf4j;
import com.epii000.model.RpcResponse;

import java.util.concurrent.Callable;

@Slf4j
public class NoRetryStrategy implements RetryStrategy {
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
