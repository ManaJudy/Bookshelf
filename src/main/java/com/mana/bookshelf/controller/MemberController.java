package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(MemberDTO memberDTO) {
        MemberDTO createdMember = memberService.createMember(memberDTO);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }
}
