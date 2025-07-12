package shop.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.DTO.ContactDTO;

@Controller
public class ContactController {


    @GetMapping("/contact")
    public ModelAndView contact(HttpServletRequest request){
        request.getSession(true);
        return new ModelAndView("contact");
    }

    @PostMapping("/contact")
    public ModelAndView contact(@Valid ContactDTO contactDTO){
        return new ModelAndView("redirect:/");
    }

    @ModelAttribute
    ContactDTO contactDTO() {
        return new ContactDTO();
    }
}
