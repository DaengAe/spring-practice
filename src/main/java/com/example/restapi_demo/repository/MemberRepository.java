package com.example.restapi_demo.repository;

import com.example.restapi_demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmail(String email);

    Optional<Object> findByEmailAndPassword(String email, String password);
}
