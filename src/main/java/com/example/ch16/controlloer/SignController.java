package com.example.ch16.controlloer;

import com.example.ch16.dto.SignInResultDto;
import com.example.ch16.dto.SignupResultDto;
import com.example.ch16.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-api")
public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService =  signService;
    }
    @PostMapping("/sign-in")
    public SignInResultDto signIn(@RequestParam String id, @RequestParam String password) throws RuntimeException {
        SignInResultDto signInResultDto = signService.signIn(id, password);
        if (signInResultDto.getCode() == 0) {
            System.out.println("[SignIn] 정상적으로 로그인 되었습니다. ");
            System.out.println("token : " + signInResultDto.getToken());
        }
        return  signInResultDto;
    }
    @PostMapping("/sign-up")
    public SignupResultDto signUp(
            @RequestParam String id, @RequestParam String password, @RequestParam String name,@RequestParam String email, @RequestParam String role) throws RuntimeException {
        SignupResultDto signupResultDto = signService.signUp(id, password, name, email, role);
        return signupResultDto;
    }

    @GetMapping("/exception")
    public void exception() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }
}

