package com.mana.bookshelf.service;

import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.repository.MemberRepository;
import com.mana.bookshelf.converter.entitytodto.MemberToMemberDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final MemberToMemberDTO memberToMemberDTO;

    public AuthService(MemberRepository memberRepository, MemberToMemberDTO memberToMemberDTO) {
        this.memberRepository = memberRepository;
        this.memberToMemberDTO = memberToMemberDTO;
    }

    public MemberDTO authenticateMember(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!member.getPassword().equals(password))
            throw new IllegalArgumentException("Invalid email or password");
        return memberToMemberDTO.apply(member);
    }

    public MemberDTO authenticateAdmin(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!member.getPassword().equals(password))
            throw new IllegalArgumentException("Invalid email or password");
        if (!member.isAdmin())
            throw new IllegalArgumentException("User is not an admin");
        return memberToMemberDTO.apply(member);
    }
}
