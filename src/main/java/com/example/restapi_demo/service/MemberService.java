package com.example.restapi_demo.service;

import com.example.restapi_demo.entity.Member;
import com.example.restapi_demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member create(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않음. id: " + id));
    }

    @Transactional
    public Member update(Long id, Member member) {
        Member target = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않음. id: " + id));
        target.patch(member);
        return memberRepository.save(target);
    }

    public Member delete(Long id) {
        Member target = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않음. id: " + id));
        memberRepository.delete(target);
        return target;
    }


    public Member login(String email, String password) {
        return (Member) memberRepository.findByEmailAndPassword(email, password).orElse(null);
    }


}
