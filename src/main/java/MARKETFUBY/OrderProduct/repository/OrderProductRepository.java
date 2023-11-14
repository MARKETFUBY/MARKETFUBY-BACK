package MARKETFUBY.OrderProduct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Order.domain.Order;
import MARKETFUBY.OrderProduct.domain.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
	List<OrderProduct> findAllByOrder(Order order);
	Optional<OrderProduct> findTop1ByOrder(Order order);
}
