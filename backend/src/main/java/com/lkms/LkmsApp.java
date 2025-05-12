package com.lkms;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.lkms.mapper")
public class LkmsApp {
    public static void main(String[] args) {
        SpringApplication.run(LkmsApp.class,args);
    }
}
