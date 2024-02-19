package com.zwfcyy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Discription: 配置类
 * @Author: zwf
 * @Date: 2022-11-09 18:49:29
 */
@Slf4j
public class ConfigProperties {

    // 默认配置文件路径
    private static final String DEFAULT_CONFIG_LOCATION = "config.properties";

    // 配置属性对象
    private static final Properties config = new Properties();

    static {
        try {
            // 在类加载时加载默认配置文件
            load(DEFAULT_CONFIG_LOCATION);
        } catch (IOException e) {
            // 如果加载失败，记录错误日志
            log.error("Failed to load default configuration file '{}': {}", DEFAULT_CONFIG_LOCATION, e.getMessage());
        }
    }


    /**
     * 加载配置文件到内存中。
     *
     * @author zwf
     * @param: fileClassPath 配置文件在类路径下的路径
     * @since created in 2024/1/18 12:26
     */
    public static void load(String fileClassPath) throws IOException {
        try (InputStream in = getConfigStream(fileClassPath)) {
            // 通过输入流加载配置文件内容到 Properties 对象
            config.load(in);
        }
    }

    /**
     * 获取配置文件的输入流。
     *
     * @author zwf
     * @param: fileClassPath 配置文件在类路径下的路径
     * @return: java.io.InputStream
     * @since created in 2024/1/18 12:26
     */
    private static InputStream getConfigStream(String fileClassPath) throws IOException {
        // 使用 Spring 的 ClassPathResource 获取配置文件的输入流
        Resource resource = new ClassPathResource(fileClassPath);
        return resource.getInputStream();
    }

    /**
     * 获取配置项的值。
     *
     * @author zwf
     * @param: key 配置项的键
     * @return: java.lang.String
     * @since created in 2024/1/18 12:26
     */
    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}
