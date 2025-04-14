package home.hunmukblog.domain.member.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResponseDto {

    private Integer status;
    private Data data;

    @Builder
    @lombok.Data
    public static class Data {
        private Token tokens;
        private User user;
    }

    @Builder
    @lombok.Data
    public static class Token {
        private String accessToken;
        private String refreshToken;
        private String tokenType = "Bearer";
        private long expiresIn = 3600;
        private long refreshTokenExpiresIn = 86400;
    }

    @Builder
    @lombok.Data
    public static class User {
        private String userId;
        private String userName;
        private String email;
        private String type ;
    }

}


