package MARKETFUBY.Product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long productId);
}
