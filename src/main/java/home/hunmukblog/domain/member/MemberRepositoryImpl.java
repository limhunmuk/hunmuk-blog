package home.hunmukblog.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.domain.member.entity.QMember;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> searchMemberList(MemberSearch memberSearch) {
        return queryFactory
                .selectFrom(QMember.member)
                .fetch();
    }
}
