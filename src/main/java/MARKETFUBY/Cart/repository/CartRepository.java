package MARKETFUBY.Cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Cart.domain.Cart;
import MARKETFUBY.Member.domain.Member;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findTopByMemberOrderByCartIdDesc(Member member);
	Optional<Cart> findByCartId(Long cartId);
}
