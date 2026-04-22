package com.example.capstone2026.controller;

import com.example.capstone2026.entity.Member;
import com.example.capstone2026.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:63342"})
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Member member) {
        try {
            Member savedMember = memberRepository.save(member);
            return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>("회원가입 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member loginRequest) {
        Optional<Member> member = memberRepository.findByUserId(loginRequest.getUserId());

        if (member.isPresent() && member.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(member.get());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("아이디 또는 비밀번호가 틀렸습니다.");
    }
}
