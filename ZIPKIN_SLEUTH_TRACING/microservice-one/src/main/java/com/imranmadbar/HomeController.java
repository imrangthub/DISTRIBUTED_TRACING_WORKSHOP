package com.imranmadbar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/microservice-one")
public class HomeController {

    @RequestMapping("/test")
    public String getList() {
        System.out.println("MessageFrom Microservice One");
        return "MessageFrom Microservice One";
    }


}
