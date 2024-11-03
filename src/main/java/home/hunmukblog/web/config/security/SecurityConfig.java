package home.hunmukblog.web.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (webSecurity) -> webSecurity.ignoring()
                .requestMatchers(request -> request.getServletPath().equals("/favicon.ico"))
                .requestMatchers(request -> request.getServletPath().equals("/error"));
    }

    @Bean
    public SecurityFilterChain memberAdminSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/auth/login").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(formLogin ->
                        formLogin
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/loginProc")
                                .defaultSuccessUrl("/")

                )
                .rememberMe(
                        rememberMe -> rememberMe.rememberMeParameter("remember")
                                .alwaysRemember(false)
                                .tokenValiditySeconds(60 * 60 * 24 * 7)
                );




        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
