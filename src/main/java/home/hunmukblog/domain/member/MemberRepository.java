package home.hunmukblog.domain.member;

import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>,
                                            QuerydslPredicateExecutor<Member>, MemberRepositoryCustom {

    @Query("SELECT a FROM Member a WHERE a.loginId = :loginId")
    Optional<Member> findByLoginId(@Param("loginId") String loginId);


}
