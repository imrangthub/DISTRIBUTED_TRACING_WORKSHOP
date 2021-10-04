package com.imranmadbar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String testMsg() {
        System.out.println("MessageFrom Microservice Three");
        return "MessageFrom Microservice Three";
    }

    @RequestMapping("/getMsg")
    public String getList() {
        LOG.info("Inside Microservice3getMsg");
        System.out.println("MessageFrom Microservice Three");
        return "MessageFrom Microservice Three";
    }

//    @RequestMapping("/getMsg")
//    public String getList() {
//        LOG.info("Inside Microservice3getMsg");
//        System.out.println("MessageFrom Microservice Three");
//        return "MessageFrom Microservice Three";
//    }

//    @RequestMapping("/getMsg1")
//    public String getList() {
//        LOG.info("Inside Microservice3getMsg");
//        System.out.println("MessageFrom Microservice Three");
//        try{
//            throw new RuntimeException("Exception from Microservice2");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return "MessageFrom Microservice Three";
//    }


}
