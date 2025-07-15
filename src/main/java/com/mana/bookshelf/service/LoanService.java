package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.dtotoentity.LoanDTOToLoan;
import com.mana.bookshelf.converter.entitytodto.LoanToLoanDTO;
import com.mana.bookshelf.dto.LoanDTO;
import com.mana.bookshelf.entity.Loan;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanDTOToLoan loanDTOToLoan;
    private final LoanToLoanDTO loanToLoanDTO;

    public LoanService(LoanRepository loanRepository, LoanDTOToLoan loanDTOToLoan, LoanToLoanDTO loanToLoanDTO) {
        this.loanRepository = loanRepository;
        this.loanDTOToLoan = loanDTOToLoan;
        this.loanToLoanDTO = loanToLoanDTO;
    }

    public void verifyLoan(Loan loan) {
        Member member = loan.getMember();
        if (member.getSubscriptionEndDate().isAfter(loan.getStartDate()))
            throw new IllegalStateException("Member's subscription is not active for the loan start date.");
        if (loanRepository.countByMemberIdAndIsReturnedFalse(member.getId(), loan.getStartDate()) > member.getSubscriptionType().getQuota())
            throw new IllegalStateException("Member has reached the loan quota for their subscription type.");
        if (loan.getBookCopy() == null)
            throw new IllegalStateException("No available book copy for the loan.");
        Integer ageRating = loan.getBookCopy().getBook().getAgeRating();
        if (ageRating != null && ageRating > member.getAge(loan.getStartDate()))
            throw new IllegalStateException("Member's age does not meet the book's age rating requirements.");
        if (member.getPenaltyEndDate() != null && member.getPenaltyEndDate().isAfter(loan.getStartDate()))
            throw new IllegalStateException("Member has an active penalty that prevents new loans.");
    }

    public LoanDTO createLoan(LoanDTO loanDTO) {
        Loan loan = loanDTOToLoan.apply(loanDTO);
        if (loan.getStartDate() == null) loan.setStartDate(LocalDate.now());
        loan.setEndDate(null);
        loan.setIsReturned(false);
        verifyLoan(loan);
        loan = loanRepository.save(loan);
        return loanToLoanDTO.apply(loan);
    }
}
