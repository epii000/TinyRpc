package org.example.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.RpcRequest;
import org.example.model.RpcResponse;

import java.io.IOException;

/**
 * 优点：
 * - 易读性好
 * - 跨语言支持广泛
 * 缺点：
 * - 序列化后数据量较大
 * - 不能很好处理复杂数据结构和循环引用
 */
public class JsonSerializer implements Serializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
        T obj = OBJECT_MAPPER.readValue(data, clazz);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj, clazz);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj, clazz);
        }
        return obj;
    }

    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> clazz) throws IOException {
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];
            if (!type.isAssignableFrom(args[i].getClass())) {
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(argBytes, type);
            }
        }
        return clazz.cast(rpcRequest);
    }

    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> clazz) throws IOException {
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes, rpcResponse.getDataType()));
        return clazz.cast(rpcResponse);
    }
}
