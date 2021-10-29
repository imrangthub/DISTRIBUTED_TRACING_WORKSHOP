package imranmadbar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class HomeController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String testMsg() {
        System.out.println("MessageFrom Microservice Five");
        return "MessageFrom Microservice Five Root";
    }


    @RequestMapping("/getMsg1")
    public String getList1() {
        LOG.info("Inside Microservice5getMsg");
        return "MessageFrom Microservice Five";
    }



}
