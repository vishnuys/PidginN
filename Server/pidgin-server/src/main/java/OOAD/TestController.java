package OOAD;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Controller
public class TestController {
 
    @RequestMapping("/test")
    public String viewHome() {
        return "test.html";
    }
}