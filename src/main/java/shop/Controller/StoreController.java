package shop.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoreController {


    @GetMapping("/store")
    public ModelAndView store (){
        System.out.println("Store controller");
        return new ModelAndView("storeProducts");
    }
}
