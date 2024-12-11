package home.hunmukblog.domain.inquiry;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.inquiry.dto.InquirySearch;
import home.hunmukblog.domain.inquiry.dto.InquiryView;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static home.hunmukblog.domain.inquiry.entity.QInquiry.inquiry;
import static home.hunmukblog.domain.post.entity.QPost.post;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class InquiryRepositoryImpl implements InquiryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public InquiryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<InquiryView> searchInquiryList(InquirySearch search) {

            return queryFactory
                    .select(
                            Projections.fields(InquiryView.class,
                            inquiry.id,
                            inquiry.title,
                            inquiry.content,
                            inquiry.regId,
                            inquiry.regDt
                        )
                    )
                    .from(inquiry)
                    .where(

                            //likeTitle(search.getTitle()),
                            //likeContent(search.getContent())
                    )
                    .orderBy(inquiry.id.desc())
                    .fetch();
    }
}
