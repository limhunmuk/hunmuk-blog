package home.hunmukblog.web;


import home.hunmukblog.web.config.jwt.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtTokenTest {

    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();


    @Test
    @DisplayName("토큰 생성 테스트")
    public void test(){

        String accessToken = jwtTokenUtil.createToken("limhunmuk");
        System.out.println("accessToken = " + accessToken);

        String refreshToken = jwtTokenUtil.createRefreshToken("limhunmuk");
        System.out.println("refreshToken = " + refreshToken);

        String getToken = jwtTokenUtil.getToken(accessToken);
        //Assertions.assertEquals(accessToken, checkedAccess);
        System.out.println("getToken = " + getToken);

        boolean chkAccessToken = jwtTokenUtil.validateToken(accessToken);
        System.out.println("엑세스토큰 유효성 검사 > " + chkAccessToken);

        String getRefreshToken = jwtTokenUtil.getToken(refreshToken);
        //Assertions.assertEquals(refreshToken, checkedRefresh);
        System.out.println("getRefreshToken = " + getRefreshToken);

        boolean chkRefreshToken = jwtTokenUtil.validateToken(refreshToken);
        System.out.println("리프레시토큰 유효성 검사 > " + chkRefreshToken);


    }
}
