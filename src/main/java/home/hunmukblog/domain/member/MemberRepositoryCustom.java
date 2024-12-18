package home.hunmukblog.domain.member;

import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.dto.MemberView;
import home.hunmukblog.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberView> searchMemberList(MemberSearch memberSearch);

    MemberView searchMemberDetail(MemberSearch memberSearch);
}
