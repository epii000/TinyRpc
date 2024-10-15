package org.example.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 优点：
 * - 高效二进制序列化
 * - 跨语言支持广泛，并提供了多种语言的实现库
 * - 支持版本化和前后版本兼容性
 * 缺点：
 * - 配置相对复杂
 * - 对象的序列化格式不易读懂，不便于调试
 */
public class KryoSerializer implements Serializer {

    /**
     * kryo 线程不安全，使用 ThreadLocal 保证每个线程只有一个kryo
     */
    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        // 设置动态序列化和反序列化类，不提前注册所有类可能有线程安全问题
        kryo.setReferences(false);
        return kryo;
    });

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        KRYO_THREAD_LOCAL.get().writeObject(output, obj);
        output.close();
        return baos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Input input = new Input(bais);
        T result = KRYO_THREAD_LOCAL.get().readObject(input, clazz);
        input.close();
        return result;
    }
}
