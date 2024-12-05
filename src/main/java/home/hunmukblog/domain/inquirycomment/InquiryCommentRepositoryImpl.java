package home.hunmukblog.domain.inquirycomment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.inquirycomment.entity.InquiryComment;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static home.hunmukblog.domain.inquirycomment.entity.QInquiryComment.inquiryComment;

@Repository
public class InquiryCommentRepositoryImpl implements InquiryCommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public InquiryCommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<InquiryComment> searchInquiryCommentList(Long id) {
        return queryFactory
                .selectFrom(inquiryComment)
                .where(inquiryComment.inquiryId.eq(Math.toIntExact(id)))
                .orderBy(inquiryComment.regDt.desc())
                .fetch();
    }
}

