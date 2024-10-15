package org.example.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.yaml.YamlUtil;
import org.example.constant.RpcConstant;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class ConfigUtils {

    /**
     * 加载配置对象
     *
     * @param tClass 配置类
     * @param prefix 配置前缀
     * @return 配置类实例
     * @param <T> 配置类类型
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        if (StrUtil.isBlank(prefix)) {
            prefix = RpcConstant.DEFAULT_CONFIG_PREFIX; // 使用常量作为默认前缀
        }
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 从不同环境中加载配置对象，自动识别配置文件类型
     *
     * @param tClass       配置类
     * @param prefix       配置前缀
     * @param environment  环境标识
     * @return             配置类实例
     * @param <T>          配置类类型
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        // 构建配置文件名称
        String baseName = "application";
        if (StrUtil.isNotBlank(environment)) {
            baseName += "-" + environment;
        }

        // 尝试加载文件，优先级：.properties > .yml > .yaml
        try {
            String propertiesFilePath = baseName + ".properties";
            InputStream propertiesStream = ConfigUtils.class.getClassLoader().getResourceAsStream(propertiesFilePath);
            if (propertiesStream != null) {
                Props props = new Props(propertiesFilePath);
                return props.toBean(tClass, prefix);
            }

            // 加载 YAML 配置文件
            T config = loadYamlConfig(tClass, prefix, baseName + ".yml");
            if (config != null) {
                return config;
            }

            config = loadYamlConfig(tClass, prefix, baseName + ".yaml");
            if (config != null) {
                return config;
            }

            throw new IllegalArgumentException("未找到任何支持的配置文件: " + baseName);

        } catch (Exception e) {
            throw new RuntimeException("加载配置文件失败", e);
        }
    }

    /**
     * 加载 YAML 配置文件
     *
     * @param tClass       配置类
     * @param prefix       配置前缀
     * @param filePath     配置文件路径
     * @param <T>          配置类类型
     * @return             配置类实例
     */
    private static <T> T loadYamlConfig(Class<T> tClass, String prefix, String filePath) {
        InputStream yamlStream = ConfigUtils.class.getClassLoader().getResourceAsStream(filePath);
        if (yamlStream != null) {
            Reader reader = new InputStreamReader(yamlStream);
            Map<String, Object> yamlData = YamlUtil.load(reader);
            if (StrUtil.isNotBlank(prefix)) {
                Map<String, Object> prefixedData = (Map<String, Object>) yamlData.get(prefix);
                if (prefixedData != null) {
                    return BeanUtil.toBean(prefixedData, tClass);
                }
            } else {
                return BeanUtil.toBean(yamlData, tClass);
            }
        }
        return null; // 返回 null 表示未找到该文件
    }
}
