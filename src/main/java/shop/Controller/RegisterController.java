package shop.Controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/user/sign_up")
    public ModelAndView sign_up(@Valid RegisterUserDTO registerUserDTO,
                                BindingResult bindingResult) {

        boolean emailCheck = userService.checkEmailExist(registerUserDTO.getEmail());
        boolean passwordCheck = registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword());
        boolean isValidUserRegistration = !emailCheck && passwordCheck && !bindingResult.hasErrors();

        if (isValidUserRegistration) {
            boolean isRegisterNewUSer = userService.registerNewUser(registerUserDTO);
            if (isRegisterNewUSer) {
                return new ModelAndView("redirect:/");
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

    @ModelAttribute
    RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }
}
