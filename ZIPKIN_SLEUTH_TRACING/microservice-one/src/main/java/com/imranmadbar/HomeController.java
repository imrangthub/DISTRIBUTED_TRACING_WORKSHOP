package com.imranmadbar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String testMsg() {
        System.out.println("MessageFrom Microservice One");
        return "MessageFrom Microservice Root";
    }


    @RequestMapping("/getMsg")
    public String getList() {
        LOG.info("Inside Microservice1getMsg");
        System.out.println("Calling  Microservice Two..");
        String requestRes = restTemplate.getForObject("http://microservice-two/getMsg",
                String.class);
        LOG.info("The response received by Microservice1getMsg: " + requestRes);
        System.out.println("Microservice Two Res: "+requestRes);
        return requestRes;
    }

    @RequestMapping("/getMsg1")
    public String getList1() {
        LOG.info("Inside Microservice1getMsg");
        return "MessageFrom Microservice One";
    }

    @RequestMapping("/getMsg2")
    public String getList2() {
        LOG.info("Inside Microservice1getMsg");
        System.out.println("Calling  Microservice Two..");
        String requestRes = restTemplate.getForObject("http://microservice-two/getMsg",
                String.class);
        LOG.info("The response received by Microservice1getMsg: " + requestRes);
        System.out.println("Microservice Two Res: "+requestRes);
        return requestRes;
    }


}
