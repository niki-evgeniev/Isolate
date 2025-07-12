package shop.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.DTO.UserDto.RegisterUserDTO;


@Controller
public class LoginController {

    @GetMapping("/user/sign_in")
    public ModelAndView login(HttpServletRequest request) {
        request.getSession(true);
        System.out.println("login controller");
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


    @RequestMapping("/users/login-error")
    public ModelAndView errorLogin(HttpServletRequest request) {
        request.getSession(true);
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("bad_credentials", true);
        System.out.println("ERROR LOGIN");
        return modelAndView;
    }

    @ModelAttribute
    RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }

}
