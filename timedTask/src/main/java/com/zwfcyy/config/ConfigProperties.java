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
    public static final String CONFIG_LOCATION = "config.properties";

    private static Properties config = new Properties();

    static {
        try {
            load(CONFIG_LOCATION);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void load(String fileClassPath) throws IOException {
        Resource resource = new ClassPathResource(fileClassPath);
        InputStream in = resource.getInputStream();
        config.load(in);
        in.close();
    }

    public static String getPropertie(String key) {
        return config.getProperty(key);
    }
}
