package com.example.feignstudy2;

import com.example.feignstudy2.jsch.SFTPUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class FeignStudy2Application {

    public static void main(String[] args) {
        SpringApplication.run(FeignStudy2Application.class, args);

    }
}
