package shop.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import shop.DTO.UserDto.RegisterUserDTO;
import shop.Entity.User;
import shop.Repository.UserRepository;
import shop.Service.UserService;

import java.util.Optional;

@Controller
public class RegisterController {

    private final UserService userService;
    private final UserRepository userRepository;

    public RegisterController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/sign_up")
    public ModelAndView sign_up(@Valid RegisterUserDTO registerUserDTO,
                                BindingResult bindingResult, HttpServletRequest request) {
        boolean emailCheck = userService.checkEmailExist(registerUserDTO.getEmail());
        boolean passwordCheck = registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword());
        boolean isValidUserRegistration = !emailCheck && passwordCheck && !bindingResult.hasErrors();

        if (isValidUserRegistration) {
            boolean isRegisterNewUSer = userService.registerNewUser(registerUserDTO);
            if (isRegisterNewUSer) {

                return new ModelAndView("verify").addObject("register", true);
            }
        }
        ModelAndView modelAndView = new ModelAndView("login");
        if (!passwordCheck) {
            bindingResult.rejectValue("confirmPassword", "passwordNotMatch");
            System.out.println("Confirm password not match");
        }
        if (emailCheck) {
            bindingResult.rejectValue("email", "registerEmailExist");
            System.out.println("Email exist in DB");
        }
        boolean register = true;
        modelAndView.addObject("registerTab", register);
        return modelAndView;
    }

    @GetMapping("/user/verify")
    public ModelAndView verifyUser(@RequestParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView("verify");
        boolean isVerifyUser = userService.verifyUser(token);
        modelAndView.addObject("isRegister", isVerifyUser);
        return modelAndView;
    }

    @ModelAttribute
    RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }


//@GetMapping("/user/verify")
//public ModelAndView verifyUser() {
//    ModelAndView modelAndView = new ModelAndView("verify");
//    modelAndView.addObject("isRegister", true);
//    return modelAndView;
//}
}
