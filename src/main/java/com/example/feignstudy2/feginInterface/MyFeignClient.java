package com.example.feignstudy2.feginInterface;

import com.example.feignstudy2.dto.Human;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "my-client2", url = "localhost:8080/example/human")
public interface MyFeignClient {

    @GetMapping(produces = "application/json")
    public List<Human> getHumans();

}
