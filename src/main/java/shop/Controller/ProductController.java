package shop.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.DTO.Porduct.AddProductDTO;
import shop.Service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/add")
    public ModelAndView testTemplate(HttpServletRequest request) {
        request.getSession(true);
        return new ModelAndView("addProduct");
    }

    @PostMapping("/product/add")
    public ModelAndView addProduct(@Valid AddProductDTO addProductDTO, BindingResult bindingResult) {

        productService.addProduct(addProductDTO);
        System.out.println();
        return new ModelAndView("redirect:/");
    }

    @ModelAttribute
    AddProductDTO addProductDTO() {
        return new AddProductDTO();
    }
}
