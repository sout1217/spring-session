package com.example.springsession.contorller;


import com.example.springsession.entity.Member;
import com.example.springsession.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping({"/", "index"})
    public String indexPage() {
        log.info("GET /index");
        return "index";
    }


    @GetMapping("sign-in")
    public String signIn() {
        log.info("GET /sign-in");

        return "sign-in";
    }

    @PostMapping("sign-in")
    public String signIn(@RequestParam String username, @RequestParam String password) {
        log.info("POST /sign-in");

        log.error("name -> {}, password -> {}", username, password);

        return "redirect:/";
    }


    @GetMapping("sign-up")
    public String signUp() {
        log.info("GET /sign-up");

        return "sign-up";
    }

    @PostMapping("sign-up")
    public String signUp(@RequestParam String username, @RequestParam String password) {
        log.info("POST /sign-up");


        Member member = Member.create(username, passwordEncoder.encode(password));

        memberRepository.save(member);

        return "redirect:/sign-in";
    }

    @GetMapping("success")
    public String success() {
        log.info("GET /success");

        return "success";
    }

    @GetMapping("failure")
    public String failure() {
        log.info("GET /failure");

        return "failure";
    }

    @GetMapping("expired")
    public String expired() {
        log.info("GET /expired");

        return "expired";
    }

    @GetMapping("invalid")
    public String invalid() {
        log.info("GET /invalid");

        return "invalid";
    }

    @GetMapping("auth")
    public String auth() {
        return "auth";
    }
}
