package MARKETFUBY.BigCategory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.BigCategory.domain.BigCategory;

public interface CategoryRepository extends JpaRepository<BigCategory, Long> {
}
