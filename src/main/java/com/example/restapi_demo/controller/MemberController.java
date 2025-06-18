package com.example.restapi_demo.controller;

import com.example.restapi_demo.dto.LoginFrom;
import com.example.restapi_demo.dto.MemberForm;
import com.example.restapi_demo.entity.Member;
import com.example.restapi_demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<Member> create(@RequestBody MemberForm dto) {
        Member created = memberService.create(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<Member>> members() {
        List<Member> members = memberService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Member> show(@PathVariable Long id) {
        Member member = memberService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PatchMapping("/api/members/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody MemberForm dto) {
        Member updated = memberService.update(id, dto.toEntity());
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Member> delete(@PathVariable Long id) {
        Member delete = memberService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(delete);
    }



    @PostMapping("/api/login")
    public ResponseEntity<String> login (@RequestBody LoginFrom dto, HttpSession session) {
        Member member = memberService.login(dto.getEmail(), dto.getPassword());
        if (member != null) {
            session.setAttribute("loginId", member.getId());
            session.setAttribute("loginEmail", member.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공");
    }

    @GetMapping("/api/user/current")
    public ResponseEntity<String> getCurrentUser(HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        return (loginEmail != null) ?
                ResponseEntity.status(HttpStatus.OK).body(loginEmail + "로그인 중") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되지 않은 상태");
    }


}
