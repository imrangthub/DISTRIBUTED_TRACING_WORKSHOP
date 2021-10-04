package com.imranmadbar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/microservice-three")
public class HomeController {

    @RequestMapping("/test")
    public String getList() {
        System.out.println("MessageFrom Microservice Three");
        return "MessageFrom Microservice Three";
    }


}
