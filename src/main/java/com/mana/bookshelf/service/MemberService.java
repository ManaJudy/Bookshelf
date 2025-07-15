package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.dtotoentity.MemberDTOToMember;
import com.mana.bookshelf.converter.entitytodto.MemberToMemberDTO;
import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberDTOToMember memberDTOToMember;
    private final MemberToMemberDTO memberToMemberDTO;

    public MemberService(MemberRepository memberRepository, MemberDTOToMember memberDTOToMember, MemberToMemberDTO memberToMemberDTO) {
        this.memberRepository = memberRepository;
        this.memberDTOToMember = memberDTOToMember;
        this.memberToMemberDTO = memberToMemberDTO;
    }

    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = memberDTOToMember.apply(memberDTO);
        member = memberRepository.save(member);
        return memberToMemberDTO.apply(member);
    }
}
