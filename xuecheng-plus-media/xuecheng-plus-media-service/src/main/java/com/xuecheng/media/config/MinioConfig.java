package com.xuecheng.media.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cmy
 * @version 1.0
 * @description TODO
 * @date 2023/3/1 16:22
 */
@Configuration
public class MinioConfig {


    @Value("${minio.endpoint}")//地址
    private String endpoint;
    @Value("${minio.accessKey}")//账号
    private String accessKey;
    @Value("${minio.secretKey}")//密码
    private String secretKey;

    @Bean
    public MinioClient minioClient() {

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();
        return minioClient;
    }
}
