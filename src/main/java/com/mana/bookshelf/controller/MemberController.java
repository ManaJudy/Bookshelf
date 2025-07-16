package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.dto.MemberDetailDTO;
import com.mana.bookshelf.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO createdMember = memberService.createMember(memberDTO);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @GetMapping("/add")
    public String addMemberForm() {
        return "members/add_member"; // This should return the view name for creating a member
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getMembers() {
        List<MemberDTO> members = memberService.getMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<MemberDetailDTO> getMemberDetails(@PathVariable Long id) {
        MemberDetailDTO memberDetails = memberService.getDetailById(id);
        return new ResponseEntity<>(memberDetails, HttpStatus.OK);
    }
}
