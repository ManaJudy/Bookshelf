package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.MemberDetailDTO;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.repository.LoanRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;

@Component
public class MemberToMemberDetailDTO implements Function<Member, MemberDetailDTO> {
    private final LoanRepository loanRepository;

    public MemberToMemberDetailDTO(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public MemberDetailDTO apply(Member member) {
        if (member == null) return null;
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setId(member.getId());
        memberDetailDTO.setName(member.getFirstName() + " " + member.getLastName());
        memberDetailDTO.setSubscription(member.getSubscriptionType().getName());
        memberDetailDTO.setSubscriptionStartDate(member.getSubscriptionStartDate());
        memberDetailDTO.setSubscriptionEndDate(member.getSubscriptionEndDate());
        memberDetailDTO.setQuota(loanRepository.countByMemberIdAndIsReturnedFalse(member.getId(), LocalDate.now()));
        memberDetailDTO.setPenalty(member.getPenaltyEndDate());
        return memberDetailDTO;
    }
}
