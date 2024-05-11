package lee.dongha.dietproject.member.repository;

import lee.dongha.dietproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByUsername(String username);
    Member findMemberByUsername(String username);
}
