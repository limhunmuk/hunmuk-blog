package home.hunmukblog.web.api.service;

import home.hunmukblog.domain.member.MemberRepository;
import home.hunmukblog.domain.member.dto.MemberCreate;
import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> searchMemberList(MemberSearch memberSearch) {
        return memberRepository.searchMemberList(memberSearch);
    }

    public Member searchMemberDetail(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member saveMember(MemberCreate request) {

        Member member = Member.builder()
                .name(request.getName())
                .age(request.getAge())
                .regId("auto")
                .regDt(LocalDateTime.now())
                .build();

        return memberRepository.save(member);
    }
}
