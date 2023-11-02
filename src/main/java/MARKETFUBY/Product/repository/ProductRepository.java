package MARKETFUBY.Product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long productId);
	List<Product> findAllByOrderByPriceAsc(Pageable pageable);
	List<Product> findAllByOrderByPriceDesc();
	List<Product> findAllByOrderByDiscountDesc();
}
