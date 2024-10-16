package home.hunmukblog.domain.member;

import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;

import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom {


    @Override
    public List<Member> searchMemberList(MemberSearch memberSearch) {
        return List.of();
    }
}
