package shop.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import shop.DTO.Porduct.DetailsProductDTO;
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
        long countProducts = productService.countProducts();
        ModelAndView modelAndView = new ModelAndView("storeList");
        modelAndView.addObject("products", listAllProduct);
        modelAndView.addObject("countProducts", countProducts);
        System.out.println("Store controller");
        return modelAndView;
    }

    @GetMapping("/store/details/{url}")
    public ModelAndView details(@PathVariable String url) {
        DetailsProductDTO detailsProductDTO = productService.getDetails(url);
        if (detailsProductDTO.getName() == null || detailsProductDTO.getModel() == null){
            ModelAndView modelAndView = new ModelAndView("error/pageNotFound");
            modelAndView.addObject("productNotFound", true);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("storeProductDetails");
        modelAndView.addObject("detailsProductDTO", detailsProductDTO);
        return modelAndView;
    }
}
