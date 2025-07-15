package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.entity.Member;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MemberToMemberDTO implements Function<Member, MemberDTO> {

    @Override
    public MemberDTO apply(Member member) {
        if (member == null) return null;
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setLastName(member.getLastName());
        memberDTO.setFirstName(member.getFirstName());
        memberDTO.setBirthDate(member.getBirthDate());
        memberDTO.setAddress(member.getAddress());
        memberDTO.setSubscriptionTypeId(member.getSubscriptionType().getId());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setSubscriptionStartDate(member.getSubscriptionStartDate());
        return memberDTO;
    }
}
