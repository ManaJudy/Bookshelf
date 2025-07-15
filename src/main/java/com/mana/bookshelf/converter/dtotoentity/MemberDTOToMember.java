package com.mana.bookshelf.converter.dtotoentity;

import com.mana.bookshelf.dto.MemberDTO;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.entity.SubscriptionType;
import com.mana.bookshelf.exception.EntityNotFoundException;
import com.mana.bookshelf.repository.SubscriptionTypeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

@Component
public class MemberDTOToMember implements Function<MemberDTO, Member> {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public MemberDTOToMember(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public Member apply(MemberDTO memberDTO) {
        if (memberDTO == null) return null;
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setLastName(memberDTO.getLastName());
        member.setFirstName(memberDTO.getFirstName());
        member.setBirthDate(memberDTO.getBirthDate());
        member.setAddress(memberDTO.getAddress());
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(memberDTO.getSubscriptionTypeId())
                .orElseThrow(() -> new EntityNotFoundException("SubscriptionType not found with id: " + memberDTO.getSubscriptionTypeId()));
        member.setSubscriptionType(subscriptionType);
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());
        member.setSubscriptionStartDate(Optional.ofNullable(memberDTO.getSubscriptionStartDate()).orElse(LocalDate.now()));
        member.setSubscriptionEndDate(memberDTO.getSubscriptionEndDate());
        member.setPenaltyEndDate(memberDTO.getPenaltyEndDate());
        return member;
    }
}
