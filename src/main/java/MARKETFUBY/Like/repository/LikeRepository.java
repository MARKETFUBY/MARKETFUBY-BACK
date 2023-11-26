package MARKETFUBY.Like.repository;

import MARKETFUBY.Like.domain.Like;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository <Like, Long> {
    boolean existsByMemberAndProduct(Member member, Product product);
    Optional<Like> findByMemberAndProduct(Member member, Product product);
    List<Like> findAllByMember(Member member);
}
