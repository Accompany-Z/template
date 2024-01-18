package com.zwfcyy.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author: zwf
 * @since: 2023-12-05 16:23:28
 * @description: 获取资源列表
 */
public class JSONConfig {
    private static JSONConfig instance;

    public static synchronized JSONConfig getInstance() {
        if (instance == null)
            instance = new JSONConfig();
        return instance;
    }

    public String readJsonFile(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath + ".json");
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        try {
            return FileCopyUtils.copyToString(reader);
        } finally {
            reader.close();
        }
    }
}
