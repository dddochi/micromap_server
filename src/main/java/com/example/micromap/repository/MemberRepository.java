package com.example.micromap.repository;

import com.example.micromap.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(String user_id);

    List<Member> findAll();

    String delete(String id);

    Optional<Member> updateId(String previous_id, String new_id);

    Optional <Member> updatePassword(Member member, String new_password);

}
