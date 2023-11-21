package MARKETFUBY.CartProduct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.Cart.domain.Cart;
import MARKETFUBY.Product.domain.Product;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
	List<CartProduct> findAllByCart(Cart cart);
	Optional<CartProduct> findByProductAndCart(Product product, Cart cart);

	Boolean existsByCartAndProduct(Cart cart, Product product);
}
