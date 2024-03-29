package OOAD;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Controller
public class FrontendController {
 
    @RequestMapping("/test")
    public String viewHome() {
        return "error.html";
    }
    
    @RequestMapping("/signup")
    public String signUp() {
        return "signup.html";
    }
    
    @RequestMapping("/login")
    public String loginPage() {
        return "login.html";
    }
    
    @RequestMapping("/chat")
    public String chatPage() {
        return "chat.html";
    }
    
    @RequestMapping("/create")
    public String createPage() {
        return "create.html";
    }
}