package com.example.swaggerpractice.member.repository;

import com.example.swaggerpractice.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Optional<Member> findByEmail(String email);
}

