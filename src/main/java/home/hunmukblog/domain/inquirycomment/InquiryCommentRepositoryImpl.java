package home.hunmukblog.domain.inquirycomment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentView;
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
    public List<InquiryCommentView> searchInquiryCommentList(Long id) {

        return
                queryFactory
                .select(Projections.fields(InquiryCommentView.class,
                        inquiryComment.id,
                        inquiryComment.content,
                        inquiryComment.regId,
                        inquiryComment.regDt,
                        inquiryComment.inquiry.id.as("inquiryId")
                ))
                .from(inquiryComment)
                .where(inquiryComment.inquiry.id.eq(id))
                .orderBy(inquiryComment.regDt.desc())
                .fetch();

    }
}

