package shop.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.DTO.Porduct.ListAllProductDTO;
import shop.Entity.Product;

import java.awt.print.Pageable;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
