package com.imranmadbar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String testMsg() {
        System.out.println("MessageFrom Microservice Two");
        return "MessageFrom Microservice Two";
    }

    @RequestMapping("/getMsg")
    public String getList() {
        String requestRes="";
        LOG.info("Inside Microservice2getMsg");
        System.out.println("Calling  Microservice Three..");
        try {
            TimeUnit.SECONDS.sleep(3);
            requestRes = restTemplate.getForObject("http://microservice-three/getMsg",
                    String.class);
            LOG.info("The response received by Microservice2getMsg: " + requestRes);
            System.out.println("Microservice Three Res: "+requestRes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return requestRes;
    }


}
