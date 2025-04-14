package home.hunmukblog.web.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.hunmukblog.domain.member.MemberRepository;
import home.hunmukblog.domain.member.entity.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //Member member = (Member) authentication.getPrincipal();

        Member member = memberRepository.findByLoginId(authentication.getName()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        System.out.println("로그인 성공 회원 객체 json return >> " + member);
        //request.setAttribute("member", member);

       // log.info("[인증성공] user={}", member.getUsername());

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(member));
        response.setStatus(SC_OK);
    }
}
