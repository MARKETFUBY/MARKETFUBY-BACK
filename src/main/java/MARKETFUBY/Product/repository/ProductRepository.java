package MARKETFUBY.Product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.Product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long productId);
	List<Product> findAllByOrderByPriceAsc();
	List<Product> findAllByOrderByPriceDesc();
	List<Product> findAllByOrderByDiscountDesc();
	//List<Product> findAllByEventAndBigCategory();
	//Integer countByBigCategoryAndEvent(BigCategory bigCategory);
	List<Product> findAllByTitleContaining(String sword);
}
