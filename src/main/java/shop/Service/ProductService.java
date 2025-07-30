package shop.Service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.DTO.Porduct.AddProductDTO;
import shop.DTO.Porduct.DetailsProductDTO;
import shop.DTO.Porduct.ListAllProductDTO;


public interface ProductService {
    void addProduct(@Valid AddProductDTO addProductDTO);

    Page<ListAllProductDTO> listAllProduct(Pageable pageable);

    long countProducts();

    DetailsProductDTO getDetails(String url);

}
