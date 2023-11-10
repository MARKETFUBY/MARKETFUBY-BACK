package MARKETFUBY.Product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.Event.domain.Event;
import MARKETFUBY.Product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long productId);
	List<Product> findAllByOrderByPriceAsc();
	List<Product> findAllByOrderByPriceDesc();
	List<Product> findAllByOrderByDiscountDesc();
	List<Product> findAllByTitleContaining(String sword);
	List<Product> findByEventOrderByPriceAsc(Event event);
	List<Product> findByEventOrderByPriceDesc(Event event);
	List<Product> findByEventOrderByDiscountDesc(Event event);
	List<Product> findAllByEvent(Event event);
	Boolean existsByBigCategory(BigCategory bigCategory);
	Integer countByBigCategoryAndEvent(BigCategory bigCategory, Event event);
}
