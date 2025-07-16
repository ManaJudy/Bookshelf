package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.MemberDetailDTO;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.repository.ExtensionRepository;
import com.mana.bookshelf.repository.LoanRepository;
import com.mana.bookshelf.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;

@Component
public class MemberToMemberDetailDTO implements Function<Member, MemberDetailDTO> {
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;
    private final ExtensionRepository extensionRepository;

    public MemberToMemberDetailDTO(LoanRepository loanRepository, ReservationRepository reservationRepository, ExtensionRepository extensionRepository) {
        this.loanRepository = loanRepository;
        this.reservationRepository = reservationRepository;
        this.extensionRepository = extensionRepository;
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

        memberDetailDTO.setLoanedBooks(loanRepository.countByMemberIdAndIsReturnedFalse(member.getId(), LocalDate.now()));
        memberDetailDTO.setQuotaLoanedBooks(member.getSubscriptionType().getQuotaLoans());

        memberDetailDTO.setReservedBooks(reservationRepository.countByMemberIdAndIsApprovedTrue(member.getId()));
        memberDetailDTO.setQuotaReservedBooks(member.getSubscriptionType().getQuotaReservations());

        memberDetailDTO.setExtendedLoans(extensionRepository.countActiveByMemberId(member.getId(), LocalDate.now()));
        memberDetailDTO.setQuotaExtendedLoans(member.getSubscriptionType().getQuotaExtends());

        memberDetailDTO.setPenalty(member.getPenaltyEndDate());
        return memberDetailDTO;
    }
}
