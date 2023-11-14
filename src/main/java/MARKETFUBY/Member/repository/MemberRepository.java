package MARKETFUBY.Member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
