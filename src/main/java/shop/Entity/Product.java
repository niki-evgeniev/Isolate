package shop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table
@Entity(name = "products")
public class Product extends  BaseEntity{


    private String name;
    private String description;
    private BigDecimal price;

    private int stock; // наличност

    private String imageUrl;

    @ManyToOne
    private Category category;
}
