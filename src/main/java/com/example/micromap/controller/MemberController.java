package com.example.micromap.controller;

import com.example.micromap.domain.Member;
import com.example.micromap.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = "Member API", description = "Swagger Test API For Member")
@RequestMapping("/")
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

        member.setUserId(id);
        member.setPassword(password);

        //print contents
        System.out.println("id: "+member.getUserId());
        System.out.println("password: "+member.getPassword());

        return memberService.Join(member);
    }

    @GetMapping("/find_members")
    public List<Member> findMembers(){
        return memberService.findMembers();
    }

    @PostMapping("/delete_member")
    public String deleteMember(@RequestBody Map<String, Object>requestData){
        String id = (String) requestData.get("id");
        return memberService.deleteMember(id);
    }

    @PostMapping("/update_id")
    public Optional<Member> updateId(@RequestBody Map<String, Object>requestData){
        String previous_id = (String) requestData.get("previous_id");
        String new_id = (String) requestData.get("new_id");
        return memberService.updateId(previous_id, new_id);
    }

    @PostMapping("/update_password")
    public Optional<Member> updatePassword(@RequestBody Map<String, Object> requestData){
        String id = (String) requestData.get("id");
        String password = (String) requestData.get("password");
        String new_password = (String) requestData.get("new_password");
        Member member = new Member();
        member.setUserId(id);
        member.setPassword(password);
        return memberService.updatePassword(member, new_password);
    }
}
