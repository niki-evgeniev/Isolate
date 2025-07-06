package shop.Controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.DTO.UserDto.RegisterUserDTO;
import shop.Service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/register")
//    public ModelAndView register(){
//        System.out.println("login controller");
//        boolean register = true;
//        ModelAndView modelAndView = new ModelAndView("register");
//        modelAndView.addObject("registerTab", register);
//        return modelAndView;
//    }

    @PostMapping("/user/sign_up")
    public ModelAndView sign_up(@Valid RegisterUserDTO registerUserDTO,
                                BindingResult bindingResult) {

        boolean emailCheck = userService.checkEmailExist(registerUserDTO.getEmail());
        boolean passwordCheck = registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword());
        boolean isValidUserRegistration = !emailCheck && passwordCheck && !bindingResult.hasErrors();
        if (isValidUserRegistration) {
//           userService.registerNewUser(registerUserDTO);
            System.out.println();
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView("sign_in OLD");
        if (!passwordCheck){
            bindingResult.rejectValue("confirmPassword", "passwordNotMatch");
        }
        if (emailCheck){
            bindingResult.rejectValue("email", "registerEmailExist");
        }
        boolean register = true;
        modelAndView.addObject("registerTab", register);
        System.out.println("email exist");
        return modelAndView;
    }

    @ModelAttribute
    RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }
}
