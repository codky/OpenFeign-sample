package com.example.feignstudy2.feginInterface;

import com.example.feignstudy2.dto.Human;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "my-eureka1", url = "localhost:8000/first-service/check")
public interface EurekaTestClient {

    @GetMapping(produces = "application/json")
    public String getPort();
}
