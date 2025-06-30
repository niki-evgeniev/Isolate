package shop.Entity;

import jakarta.persistence.*;
import shop.Entity.Enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "art")
    private String art;

    @Column(name = "created", columnDefinition = "DATETIME(0)")
    private LocalDateTime created;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private User user;


    @OneToMany
            (mappedBy = "order",
                    cascade = CascadeType.ALL)
    private List<OrderItems> items;

    public Order() {
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
