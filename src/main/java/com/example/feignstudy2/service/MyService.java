package com.example.feignstudy2.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public MyService() {
        init();
    }

    void init() {
        System.out.println("init---------------------");
    }

    public void test() {
        System.out.println("MyService: " + MyService.this);

        String aaa = new String("asdfasdf");
        System.out.println(System.identityHashCode(aaa));
    }

}
