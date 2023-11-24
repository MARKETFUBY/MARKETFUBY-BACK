package MARKETFUBY.SmallCategory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.SmallCategory.domain.SmallCategory;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Long> {
	List<SmallCategory> findAllByBigCategory(BigCategory bigCategory);
}
