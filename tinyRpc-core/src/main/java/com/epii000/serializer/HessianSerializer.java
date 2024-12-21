package com.epii000.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 优点：
 * - 二进制序列化，数据量较小，网络传输效率高
 * - 支持跨语言，适用于分布式系统的服务调用
 * 缺点：
 * - 性能较JSON略低，因为二进制转化
 * - 对象必须实现Serializer接口
 */
public class HessianSerializer implements Serializer {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(baos);
        hessian2Output.writeObject(obj);
        hessian2Output.flush();
        return baos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Hessian2Input hessian2Input = new Hessian2Input(bais);
        return (T) hessian2Input.readObject(clazz);
    }
}
