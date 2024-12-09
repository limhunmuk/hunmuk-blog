package home.hunmukblog.web.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping({"/", "/api/hello"})
    public String main() {
        return "Hello, Hunmuk!";
    }
}
