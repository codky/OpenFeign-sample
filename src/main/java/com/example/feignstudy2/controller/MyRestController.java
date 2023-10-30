package com.example.feignstudy2.controller;

import com.example.feignstudy2.dto.Human;
import com.example.feignstudy2.feginInterface.EurekaTestClient;
import com.example.feignstudy2.feginInterface.EurekaTestSecond;
import com.example.feignstudy2.feginInterface.MyFeignClient;
import com.example.feignstudy2.service.MyService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    MyService myService;

    private Logger logger = LoggerFactory.getLogger(MyRestController.class);

    private final MyFeignClient myFeignClient;
    private final EurekaTestClient eurekaTestClient;
    private final EurekaTestSecond eurekaTestSecond;

    public MyRestController(MyFeignClient myFeignClient, EurekaTestClient eurekaTestClient, EurekaTestSecond eurekaTestSecond) {
        this.myFeignClient = myFeignClient;
        this.eurekaTestClient = eurekaTestClient;
        this.eurekaTestSecond = eurekaTestSecond;
    }

    @GetMapping("/a")
    public String ttttt() {
        myService.test();

        return "aaaa";
    }

    @GetMapping("/example/human")
    public List<Human> getHuman() {
        logger.info("### MyRestController 에서 /example/human 호출 ###");
        return Lists.newArrayList(
                Human.builder().name("견민석").age(31).sex("남").build(),
                Human.builder().name("선형채").age(24).sex("남").build(),
                Human.builder().name("김영빈").age(30).sex("남").build(),
                Human.builder().name("김지태").age(35).sex("남").build(),
                Human.builder().name("하태경").age(30).sex("남").build()
        );
    }

    @GetMapping("/example/feign")
    public List<Human> getFeign() {
        List<Human> humans = myFeignClient.getHumans();

        humans.stream().forEach(m->m.setAge(100));
        return humans;
    }

    @GetMapping("/eureka-test-first")
    public String getEurekaTest1() {
        String port = eurekaTestClient.getPort();
        logger.info("### MyRestController 에서 /eureka-test-first 호출 ### => port : {}", port);
        return "getEurekaTest1. port = " + port;
    }

    @GetMapping("/eureka-test-second")
    public String getEurekaTest2() {
        String port = eurekaTestSecond.getPort();
        logger.info("### MyRestController 에서 /eureka-test-second 호출 ### => port : {}", port);

        return "getEurekaTest2. port = " + port;
    }
}
