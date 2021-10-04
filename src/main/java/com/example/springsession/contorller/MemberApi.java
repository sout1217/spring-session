package com.example.springsession.contorller;

import com.example.springsession.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberRepository memberRepository;

    @GetMapping("")
    public ResponseEntity<?> getMembers() {

        return ResponseEntity.ok(memberRepository.findAll());
    }

}
