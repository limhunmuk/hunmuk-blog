package home.hunmukblog.web.api.service;

import home.hunmukblog.domain.member.MemberRepository;
import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> searchMemberList(MemberSearch memberSearch) {
        return memberRepository.searchMemberList(memberSearch);
    }
}
