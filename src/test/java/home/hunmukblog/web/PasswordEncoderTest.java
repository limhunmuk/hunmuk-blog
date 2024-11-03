package home.hunmukblog.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){

        String password = "1234";
        String encPwd = passwordEncoder.encode(password);

        System.out.println(passwordEncoder.matches(password, encPwd));

    }

}
