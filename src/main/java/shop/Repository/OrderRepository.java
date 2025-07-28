package shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.Entity.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
