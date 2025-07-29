package shop.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.DTO.Porduct.ListAllProductDTO;
import shop.Service.ProductService;


@Controller
public class StoreController {

    private final ProductService productService;

    public StoreController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/store")
    public ModelAndView store(@PageableDefault(size = 6, sort = "name") Pageable pageable,
                              HttpServletRequest request) {
        Page<ListAllProductDTO> listAllProduct = productService.listAllProduct(pageable);
        ModelAndView modelAndView = new ModelAndView("storeList");
        modelAndView.addObject("products", listAllProduct);

        System.out.println("Store controller");
        return modelAndView;
    }
}
