package shop.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity(name = "orders")
public class Order extends BaseEntity {

    private String name;
    private String art;

    @ManyToOne
    private User user;

    private LocalDateTime created;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

}
