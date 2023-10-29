package MARKETFUBY.CartProduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.Cart.domain.Cart;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
	List<CartProduct> findAllByCart(Cart cart);
}
