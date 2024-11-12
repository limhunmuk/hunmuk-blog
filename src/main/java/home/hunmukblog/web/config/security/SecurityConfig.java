package home.hunmukblog.web.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.hunmukblog.domain.member.MemberRepository;
import home.hunmukblog.web.config.handler.LoginFailHandler;
import home.hunmukblog.web.config.handler.LoginSuccessHandler;
import home.hunmukblog.web.config.jwt.JwtTokenUtil;
import home.hunmukblog.web.filter.JwtAuthenticationFilter;
import home.hunmukblog.web.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity // @PreAuthorize, @PostAuthorize, @Secured, @RolesAllowed 사용 가능하도록 하는 어노테이션

public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final MemberRepository userRepository;
    private final MemberDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/favicon.ico")
                .requestMatchers("/error");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // JwtAuthorizationFilter는 모든 요청에서 토큰을 검증하기 위해 추가됩니다.
        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(jwtTokenUtil);

        // 로그인 필터는 JwtAuthenticationFilter에서 처리합니다.
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), jwtTokenUtil, "/api/login");

        return http.authorizeHttpRequests(

                    auth ->
                            //auth.requestMatchers("/api/login").permitAll()
                            //.requestMatchers("/api/signup").authenticated()
                           //auth.anyRequest().permitAll() // 모든 요청에 대해 인증을 요구하지 않음
                            auth.requestMatchers("/", "/api/login").permitAll() // 로그인 엔드포인트 접근 허용
                            .anyRequest().authenticated() // 다른 모든 요청은 인증 필요
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // 로그인 필터 먼저 추가
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class) // JWT 인증 필터 추가
                .rememberMe(rm -> rm.rememberMeParameter("remember-me")
                        .alwaysRemember(false)
                        .tokenValiditySeconds(2592000)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    //@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        //JwtAuthenticationFilter filter = new JwtAuthenticationFilter("/api/login", objectMapper);
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager(), jwtTokenUtil, "/api/login");


        filter.setAuthenticationManager(authenticationManager());
        //filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
        filter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        filter.setAuthenticationFailureHandler(new LoginFailHandler(objectMapper));
//        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        filter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());

        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setAlwaysRemember(true);
        rememberMeServices.setValiditySeconds(3600 * 24 * 30);
        filter.setRememberMeServices(rememberMeServices);
        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
