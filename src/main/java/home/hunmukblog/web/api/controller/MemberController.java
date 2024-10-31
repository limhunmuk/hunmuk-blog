package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.member.dto.MemberCreate;
import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.web.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public List<Member> member(MemberSearch search) {

        List<Member> members = memberService.searchMemberList(search);
        return members;
    }

    @GetMapping("/member/{id}")
    public Member memberDetail(@PathVariable Long id) {
        return memberService.searchMemberDetail(id);
    }


    @PostMapping("/member")
    public Member memberCreate(@RequestBody MemberCreate request) {

        log.debug("member 등록 > request : {}", request);
        return memberService.saveMember(request);
    }
}
