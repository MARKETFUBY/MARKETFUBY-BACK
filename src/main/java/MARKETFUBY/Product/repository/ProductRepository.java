package MARKETFUBY.Product.repository;

import MARKETFUBY.Product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
