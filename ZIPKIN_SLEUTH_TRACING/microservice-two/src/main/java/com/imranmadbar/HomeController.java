package com.imranmadbar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/microservice-two")
public class HomeController {

    @RequestMapping("/test")
    public String getList() {
        System.out.println("MessageFrom Microservice two");
        return "MessageFrom Microservice two";
    }


}
