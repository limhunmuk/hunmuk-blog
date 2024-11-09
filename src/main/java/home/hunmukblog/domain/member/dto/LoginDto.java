package home.hunmukblog.domain.member.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String loginId;
    private String password;


    @Override
    public String toString() {
        return "LoginDto{" +
                "loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
