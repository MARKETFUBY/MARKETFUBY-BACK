package MARKETFUBY.BigCategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.BigCategory.domain.BigCategory;

public interface BigCategoryRepository extends JpaRepository<BigCategory, Long> {
}
