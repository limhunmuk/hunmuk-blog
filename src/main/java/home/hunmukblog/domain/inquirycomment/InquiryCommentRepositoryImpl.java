package home.hunmukblog.domain.inquirycomment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryCommentRepositoryImpl implements InquiryCommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public InquiryCommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
