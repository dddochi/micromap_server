package com.example.micromap.service;

import com.example.micromap.domain.Member;
import com.example.micromap.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String Join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getUser_id();

    }

    public void validateDuplicateMember(Member member){
        memberRepository.findById(member.getUser_id()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

}
