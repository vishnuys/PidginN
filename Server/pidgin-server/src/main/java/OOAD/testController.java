package OOAD;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
 
    @RequestMapping("/test")
    public String viewHome() {
        return "test.html";
    }
}