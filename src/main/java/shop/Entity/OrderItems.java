package shop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItems extends BaseEntity{

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;

    private BigDecimal priceAtOrder;

}
