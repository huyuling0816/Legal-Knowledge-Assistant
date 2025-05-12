package com.lkms.utils.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathConstants {
    @Value("${resource.dirPath}")
    public String tempFilePath;
}
