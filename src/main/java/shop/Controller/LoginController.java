package shop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @GetMapping("/sign_in")
    public ModelAndView login() {
        System.out.println("login controller");
        return new ModelAndView("login");
    }

    @RequestMapping("/login-error")
    public ModelAndView errorLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("bad_credentials", true);
        System.out.println("ERROR LOGIN");
        return modelAndView;
    }
}
