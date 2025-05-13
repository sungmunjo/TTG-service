package com.kr.moon.talktogether.user.user_info.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class UserInfoController {

    private final MemberService memberService;

    public UserInfoController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member save(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @GetMapping
    public List<Member> getAll() {
        return memberService.getAllMembers();
    }
}