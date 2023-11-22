package MARKETFUBY.Order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByOrderer(Member member);
}
