package com.example.micromap.repository;

import com.example.micromap.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(String user_id);

    List<Member> findAll();

    Member delete(Member member);

    Member updateId(Member member);

    Member updatePassword(Member member);

}
