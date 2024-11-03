package home.hunmukblog.web.config.security;

import home.hunmukblog.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {

    private final MemberDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Member member = (Member) memberDetailsService.loadUserByUsername(username);
        // ↑ 아이디 없으면 UsernameNotFoundException 예외 발생

        if (!passwordEncoder.matches(password, member.getPassword()))
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");

        if (!member.isAccountNonExpired())
            throw new AccountExpiredException("만료된 계정입니다. 관리자에게 문의하세요.");

        if (!member.isEnabled())
            throw new DisabledException("계정이 비활성화 되었습니다. 관리자에게 문의하세요.");

        if (!member.isAccountNonLocked())
            throw new LockedException("계정이 잠겼습니다. 관리자에게 문의하세요.");

        if (!member.isCredentialsNonExpired())
            throw new CredentialsExpiredException("비밀번호가 만료되었습니다. 비밀번호를 변경하고 로그인 해주세요.");

        return new UsernamePasswordAuthenticationToken(member, password, member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
