package com.kr.moon.talktogether.config;

import io.minio.MinioClient;
import io.minio.credentials.IamAwsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class MinioConfig {

    @Value("${storage.endpoint}")
    private String endpoint;

    @Value("${storage.region}")
    private String region;

    @Value("${storage.access-key}")
    private String accessKey;

    @Value("${storage.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        MinioClient.Builder builder = MinioClient.builder()
                .endpoint(endpoint)
                .region(region);

        // 로컬 MinIO 처럼 키가 있으면 키로, 서버(S3)처럼 키가 비어 있으면 EC2 IAM 역할의 임시 키로 접속한다
        if (StringUtils.hasText(accessKey)) {
            builder.credentials(accessKey, secretKey);
        } else {
            builder.credentialsProvider(new IamAwsProvider(null, null));
        }
        return builder.build();
    }
}
