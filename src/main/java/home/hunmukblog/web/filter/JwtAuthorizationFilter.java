package home.hunmukblog.web.filter;

import home.hunmukblog.web.config.jwt.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthorizationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String refreshTokenHeader = request.getHeader("refreshToken");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {

                String jwtToken = authHeader.substring(7); // "Bearer " 제거
                String username = jwtTokenUtil.getToken(jwtToken);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (jwtTokenUtil.validateToken(jwtToken)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }catch (JwtException e){

                // 액세스 토큰이 만료된 경우 리프레시 토큰으로 새로운 액세스 토큰 발급
                if (refreshTokenHeader != null) {
                    try {
                        String refreshToken = refreshTokenHeader.substring(7); // "Bearer " 제거
                        String username = jwtTokenUtil.getToken(refreshToken);

                        if (username != null && jwtTokenUtil.validateToken(refreshToken)) {
                            // 새로운 액세스 토큰 발급 후, 응답 헤더에 추가
                            String newAccessToken = jwtTokenUtil.createToken(username);
                            response.setHeader("Authorization", "Bearer " + newAccessToken);
                        }
                    } catch (JwtException ex) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
}
