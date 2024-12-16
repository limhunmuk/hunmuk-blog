package home.hunmukblog.domain.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.dto.MemberView;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.domain.member.entity.QMember;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static home.hunmukblog.domain.member.entity.QMember.*;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberView> searchMemberList(MemberSearch memberSearch) {
        return queryFactory
                .select(Projections.fields(
                        MemberView.class,
                        member.id,
                        member.loginId,
                        member.name,
                        member.age,
                        member.regDt,
                        member.regId
                ))
                .from(member)
                //.where(member.loginId.isNotEmpty())
                .fetch();
    }

    @Override
    public MemberView searchMemberDetail(MemberSearch memberSearch) {

        return queryFactory
                .select(Projections.fields(
                        MemberView.class,
                        member.id,
                        member.loginId,
                        member.name,
                        member.age,
                        member.regDt,
                        member.regId
                ))
                .from(member)
                .where(
                        member.id.eq(memberSearch.getId())
                )
                .fetchFirst();

    }


}
