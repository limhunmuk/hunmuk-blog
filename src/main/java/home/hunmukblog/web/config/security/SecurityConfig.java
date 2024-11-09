package home.hunmukblog.web.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.hunmukblog.domain.member.MemberRepository;
import home.hunmukblog.web.filter.LoginIdPasswordAuthFilter;
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
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
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

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/favicon.ico")
                .requestMatchers("/error");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                    authz -> authz.anyRequest().permitAll()
                )
                .addFilterBefore(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .rememberMe(rm -> rm.rememberMeParameter("remember-me")
                        .alwaysRemember(false)
                        .tokenValiditySeconds(2592000)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public LoginIdPasswordAuthFilter usernamePasswordAuthenticationFilter() {
        LoginIdPasswordAuthFilter filter = new LoginIdPasswordAuthFilter("/api/login", objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
//        filter.setAuthenticationFailureHandler(new LoginFailHandler(objectMapper));
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
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
