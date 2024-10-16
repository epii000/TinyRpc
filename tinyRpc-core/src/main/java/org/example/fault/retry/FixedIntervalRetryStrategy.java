package org.example.fault.retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import lombok.extern.slf4j.Slf4j;
import org.example.model.RpcResponse;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy {

    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfExceptionOfType(Exception.class)
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        int attemptNumber = 0;
        while (true) {
            try {
                attemptNumber++;
                log.info("Retry attempt: {}", attemptNumber);
                return retryer.call(callable);
            } catch (Exception e) {
                log.error("Retry failed after {} attempts", attemptNumber, e);
                throw e;
            }
        }
    }
}
