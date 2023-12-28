package com.example.micromap.controller;

import com.example.micromap.domain.Member;
import com.example.micromap.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody Map<String, Object> requestData){
        String id = (String) requestData.get("id");
        String password = (String) requestData.get("password");

        Member member = new Member();

        member.setUser_id(id);
        member.setPassword(password);

        return memberService.Join(member);
    }

    @GetMapping("/find_members")
    public List<Member> findMembers(){
        return memberService.findMembers();
    }
}
