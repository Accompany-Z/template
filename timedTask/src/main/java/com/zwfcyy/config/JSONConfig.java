package com.zwfcyy.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author: zwf
 * @since: 2023-12-05 16:23:28
 * @description: 获取资源列表
 */
public class JSONConfig {
    private static class Holder {
        private static final JSONConfig INSTANCE = new JSONConfig();
    }

    /**
     * 获取 JSONConfig 的实例。
     *
     * @author zwf
     * @return: com.zwfcyy.config.JSONConfig
     * @since created in 2024/1/18 12:23
     */
    public static JSONConfig getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 读取指定路径下的 JSON 文件内容。
     *
     * @author zwf
     * @param: filePath  JSON 文件的路径（不包含扩展名）
     * @return: java.lang.String
     * @since created in 2024/1/18 12:23
     */
    public String readJsonFile(String filePath) throws IOException {
        // 构建完整的文件路径
        Path fullPath = Paths.get(filePath + ".json");

        // 创建 ClassPathResource 对象，用于访问类路径下的资源
        ClassPathResource resource = new ClassPathResource(fullPath.toString());

        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            // 使用 FileCopyUtils 将文件内容转换为字符串
            return FileCopyUtils.copyToString(reader);
        }
    }

    /**
     * 读取指定路径下的 JSON 文件内容。
     *
     * @author zwf
     * @param: filePath  JSON 文件的路径（不包含扩展名）
     * @return: java.lang.String
     * @since created in 2024/1/18 12:23
     */
    public String readTaskFile(String filePath) throws IOException {
        // 构建完整的文件路径
        Path fullPath = Paths.get(File.separator + filePath + ".json");
        // 创建 ClassPathResource 对象，用于访问类路径下的资源
        ClassPathResource resource = new ClassPathResource(fullPath.toString());

        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            // 使用 FileCopyUtils 将文件内容转换为字符串
            return FileCopyUtils.copyToString(reader);
        }
    }
}
