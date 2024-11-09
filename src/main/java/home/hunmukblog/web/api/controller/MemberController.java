package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.member.dto.MemberCreate;
import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.web.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String member(Model model) {
        return "member";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }


    @GetMapping("/api/member")
    public ResponseEntity<List<Member>> member(MemberSearch search) {

        List<Member> members = memberService.searchMemberList(search);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/api/member/{id}")
    public Member memberDetail(@PathVariable Long id) {
        return memberService.searchMemberDetail(id);
    }

    @PostMapping("/api/signup")
    public void memberCreate(@RequestBody MemberCreate request) {

        log.debug("member 등록 > request : {}", request);
        memberService.saveMember(request);
    }
}
