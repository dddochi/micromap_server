package com.example.micromap.service;

import com.example.micromap.domain.Member;
import com.example.micromap.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String Join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getUserId();

    }

    public void validateDuplicateMember(Member member){
        memberRepository.findById(member.getUserId()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public String deleteMember(String id){
        memberRepository.delete(id);
        return id;
    }

    public Optional<Member> updateId(String previous_id, String new_id){
        return memberRepository.updateId(previous_id, new_id);
    }

    public Optional<Member> updatePassword(Member member, String new_password){
        return memberRepository.updatePassword(member, new_password);
    }

}
