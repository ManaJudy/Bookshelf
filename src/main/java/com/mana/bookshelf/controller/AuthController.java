package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.LoginRequestDTO;
import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDTO> authenticateMember(@RequestBody LoginRequestDTO loginRequest, HttpSession session) {
        MemberDTO authenticatedMember = authService.authenticateMember(loginRequest.getEmail(), loginRequest.getPassword());
        session.setAttribute("memberId", authenticatedMember.getId());
        return new ResponseEntity<>(authenticatedMember, HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
