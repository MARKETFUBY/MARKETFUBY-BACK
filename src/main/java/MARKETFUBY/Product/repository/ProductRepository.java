package MARKETFUBY.Product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.Event.domain.Event;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.SmallCategory.domain.SmallCategory;

public interface ProductRepository extends JpaRepository<Product, Long> {
	//그냥, 혜택순, 낮은 가격순, 높은 가격순
	//필터 적용
	//카테고리 적용
	//신상품, 베스트, 알뜰쇼핑
	Optional<Product> findById(Long productId);
	List<Product> findAllByOrderByPriceAsc();
	List<Product> findAllByOrderByPriceDesc();
	List<Product> findAllByOrderByDiscountDesc();

	//대카테고리
	List<Product> findTop12ByBigCategory(BigCategory bigCategory);
	//소카테고리
	List<Product> findTop12BySmallCategory(SmallCategory smallCategory);
	List<Product> findTop12BySmallCategoryOrderByPriceAsc(SmallCategory smallCategory);
	List<Product> findTop12BySmallCategoryOrderByPriceDesc(SmallCategory smallCategory);
	List<Product> findTop12BySmallCategoryOrderByDiscountDesc(SmallCategory smallCategory);

	//검색
	List<Product> findAllByTitleContaining(String sword);

	//신상품,베스트,알뜰쇼핑
	List<Product> findTop12ByEvent(Event event);
	List<Product> findTop12ByEventOrderByPriceAsc(Event event);
	List<Product> findTop12ByEventOrderByPriceDesc(Event event);
	List<Product> findTop12ByEventOrderByDiscountDesc(Event event);
	List<Product> findTop12ByEventAndBigCategory(Event event, BigCategory bigCategory);
	List<Product> findTop12ByEventAndBigCategoryOrderByPriceAsc(Event event, BigCategory bigCategory);
	List<Product> findTop12ByEventAndBigCategoryOrderByPriceDesc(Event event, BigCategory bigCategory);
	List<Product> findTop12ByEventAndBigCategoryOrderByDiscountDesc(Event event, BigCategory bigCategory);

	Boolean existsByBigCategory(BigCategory bigCategory);
	Integer countByBigCategoryAndEvent(BigCategory bigCategory, Event event);
}
