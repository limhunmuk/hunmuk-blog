package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.member.dto.LoginDto;
import home.hunmukblog.domain.member.dto.TokenDto;
import home.hunmukblog.web.config.jwt.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("token"); // 클라이언트가 보낸 리프레시 토큰

        try {
            // 리프레시 토큰 검증
            if (jwtTokenUtil.validateToken(refreshToken)) {
                String username = jwtTokenUtil.getToken(refreshToken);

                // 새로운 액세스 토큰 생성
                String newAccessToken = jwtTokenUtil.createToken(username);

                // 클라이언트에게 새 액세스 토큰 반환
                return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired or invalid");
        }
    }
}
