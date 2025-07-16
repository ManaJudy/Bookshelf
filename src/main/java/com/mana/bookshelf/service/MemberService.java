package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.dtotoentity.MemberDTOToMember;
import com.mana.bookshelf.converter.entitytodto.MemberToMemberDTO;
import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public void verifyMember(Member member) {
        if (memberRepository.findByEmail(member.getEmail()).isPresent())
            throw new IllegalStateException("Member with email " + member.getEmail() + " already exists.");
    }

    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = memberDTOToMember.apply(memberDTO);
        member.setSubscriptionEndDate(member.getSubscriptionStartDate().plusYears(1));
        member.setPenaltyEndDate(null);
        verifyMember(member);
        member = memberRepository.save(member);
        return memberToMemberDTO.apply(member);
    }

    public List<MemberDTO> getMembers() {
        return memberRepository.findAll().stream()
                .map(memberToMemberDTO::apply)
                .collect(Collectors.toList());
    }
}
