package com.lkms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="cos")
public class COSConfig {
    private String baseUrl;
    private String appId;
    private String secretId;
    private String secretKey;
    private String regionName;
    private String bucketName;
    private String folderPrefix;
    private String notePrefix;
}