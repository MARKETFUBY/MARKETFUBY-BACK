package MARKETFUBY.Member.repository;

import MARKETFUBY.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // fubyId 중복 검사
    Boolean existsByFubyId(String fubyId);
    Boolean existsByEmail(String email);
    Optional<Member> findByFubyId(String fubyId);
}
