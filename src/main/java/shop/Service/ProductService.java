package shop.Service;

import jakarta.validation.Valid;
import shop.DTO.Porduct.AddProductDTO;

public interface ProductService {
    void addProduct(@Valid AddProductDTO addProductDTO);
}
