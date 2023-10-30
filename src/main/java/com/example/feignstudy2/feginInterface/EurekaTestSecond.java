package com.example.feignstudy2.feginInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "my-eureka2", url = "localhost:8000/second-service/check")
public interface EurekaTestSecond {

    @GetMapping(produces = "application/json")
    public String getPort();
}
