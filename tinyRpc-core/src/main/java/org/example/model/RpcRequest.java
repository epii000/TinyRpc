package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.constant.RpcConstant;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {

    private String serviceName;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] args;

    private String serviceVersion = RpcConstant.DEAULT_SERVICE_VERSION;
}
