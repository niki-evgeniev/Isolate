package shop.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

//To testing template
@Controller
public class TestController {

    @GetMapping("/test")
    public ModelAndView testTemplate(HttpServletRequest request){
        request.getSession(true);
        System.out.println();
        return new ModelAndView("addProduct");
    }
}
