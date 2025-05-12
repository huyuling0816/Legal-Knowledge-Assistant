package com.lkms.utils.file;

import com.lkms.config.COSConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Calendar;

@Component
public class COSClientUtil {
    @Value("")
    private String defaultFilePath;

    private COSConfig config;

    private COSClient client;

    @Autowired
    public COSClientUtil(COSConfig cosConfig){
        this.config = cosConfig;
        try {
            //初始化用户身份信息(secretId,secretKey)
            COSCredentials cosCredentials = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
            Region region = new Region(cosConfig.getRegionName());
            //设置bucket的区域
            ClientConfig clientConfig = new ClientConfig(region);
            //生成cos客户端
            client = new COSClient(cosCredentials, clientConfig);
        } catch (CosClientException e) {
            e.printStackTrace();
        }
    }

    public File multipartFileToFile(MultipartFile multipartFile){
        File toFile = null;
        if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
        } else {
            InputStream ins;
            try {
                ins = multipartFile.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            toFile = new File(this.defaultFilePath+multipartFile.getOriginalFilename());
            try {
                OutputStream os = new FileOutputStream(toFile);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                ins.close();
            } catch (Exception e) {
                throw new RuntimeException("读取文件错误", e);
            }
            try {
                ins.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return toFile;
    }

    public String upload(File file,String folderPrefix, boolean deleteOriginalFile) {
        //生成唯一文件名
        String newFileName = file.getName();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        //文件在存储桶中的key
        String key = folderPrefix+"/" + year + "-" + month + "-" + day + "-" + newFileName;
        try {
            //创建存储对象的请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(config.getBucketName(), key, file);
            //执行上传并返回结果信息
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
            if(deleteOriginalFile){
                file.delete();
            }
            return config.getBaseUrl() + key;
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
