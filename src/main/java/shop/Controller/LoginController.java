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
        boolean register = true;
        ModelAndView modelAndView = new ModelAndView("sign_in");
        modelAndView.addObject("registerTab", register);
        return modelAndView;
    }

    @RequestMapping("/login-error")
    public ModelAndView errorLogin() {
        ModelAndView modelAndView = new ModelAndView("sign_in");
        modelAndView.addObject("bad_credentials", true);
        System.out.println("ERROR LOGIN");
        return modelAndView;
    }

//    @GetMapping("/login-error")
//    public ModelAndView errorLoginGet() {
//        ModelAndView modelAndView = new ModelAndView("sign_in");
//        modelAndView.addObject("bad_credentials", true);
//        return modelAndView;
//    }

}
