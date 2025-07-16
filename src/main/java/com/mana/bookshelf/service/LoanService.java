package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.dtotoentity.LoanDTOToLoan;
import com.mana.bookshelf.converter.entitytodto.LoanToLoanDTO;
import com.mana.bookshelf.dto.LoanDTO;
import com.mana.bookshelf.entity.Extension;
import com.mana.bookshelf.entity.Holiday;
import com.mana.bookshelf.entity.Loan;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.exception.EntityNotFoundException;
import com.mana.bookshelf.repository.ExtensionRepository;
import com.mana.bookshelf.repository.HolidayRepository;
import com.mana.bookshelf.repository.LoanRepository;
import com.mana.bookshelf.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final LoanDTOToLoan loanDTOToLoan;
    private final LoanToLoanDTO loanToLoanDTO;
    private final HolidayRepository holidayRepository;
    private final ExtensionRepository extensionRepository;

    public LoanService(LoanRepository loanRepository, MemberRepository memberRepository, LoanDTOToLoan loanDTOToLoan, LoanToLoanDTO loanToLoanDTO, HolidayRepository holidayRepository, ExtensionRepository extensionRepository) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.loanDTOToLoan = loanDTOToLoan;
        this.loanToLoanDTO = loanToLoanDTO;
        this.holidayRepository = holidayRepository;
        this.extensionRepository = extensionRepository;
    }

    public void verifyLoan(Loan loan) {
        Member member = loan.getMember();
        if (member.getSubscriptionEndDate().isBefore(loan.getStartDate())) {
            System.out.println("Subscription end date: " + member.getSubscriptionEndDate());
            System.out.println("Loan start date: " + loan.getStartDate());
            throw new IllegalStateException("Member's subscription is not active for the loan start date.");
        }
        if (loanRepository.countByMemberIdAndIsReturnedFalse(member.getId(), loan.getStartDate()) >= member.getSubscriptionType().getQuotaLoans()) {
            System.out.println("quota : "+ loanRepository.countByMemberIdAndIsReturnedFalse(member.getId(), loan.getStartDate()));
            System.out.println("max quota : "+ member.getSubscriptionType().getQuotaLoans());
            throw new IllegalStateException("Member has reached the loan quota for their subscription type.");
        }
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
        loan.setPrevisionEndDate(loan.getStartDate().plusDays(loan.getMember().getSubscriptionType().getMaxLoanDays()));
        List<Holiday> holidays = holidayRepository.findAll();
        for (Holiday holiday : holidays) {
            if (holiday.getLocalDate().isAfter(loan.getStartDate()) && holiday.getLocalDate().isBefore(loan.getPrevisionEndDate())) {
                loan.setPrevisionEndDate(loan.getPrevisionEndDate().plusDays(1));
            }
        }
        loan.setReturned(false);
        verifyLoan(loan);
        loan = loanRepository.save(loan);
        return loanToLoanDTO.apply(loan);
    }

    public LoanDTO returnLoan(Long loanId, LocalDate returnDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + loanId));
        if (loan.isReturned())
            throw new IllegalStateException("Loan with id " + loanId + " has already been returned.");
        loan.setReturned(true);
        loan.setEndDate(returnDate != null ? returnDate : LocalDate.now());
        loan = loanRepository.save(loan);
        int j = loan.getMember().getSubscriptionType().getPenaltyDays(); // To change // TODO
        if (loan.getEndDate().isAfter(loan.getPrevisionEndDate())) {
            Member member = loan.getMember();
            LocalDate penalityEndDate = member.getPenaltyEndDate();
            LocalDate lastPenaltyEndDate = penalityEndDate != null && penalityEndDate.isAfter(loan.getEndDate()) ?
                    penalityEndDate : loan.getEndDate();
            member.setPenaltyEndDate(lastPenaltyEndDate.plusDays(j));
            memberRepository.save(member);
        }
        return loanToLoanDTO.apply(loan);
    }

    public LoanDTO extendLoan(Long loanId, LocalDate extendDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + loanId));
        LocalDate newPrevisionEndDate = loan.getPrevisionEndDate().plusDays(loan.getLoanType().getMaxLoanDays());
        if (loan.isReturned())
            throw new IllegalStateException("Loan with id " + loanId + " has already been returned.");
        if (newPrevisionEndDate.isBefore(loan.getPrevisionEndDate()))
            throw new IllegalArgumentException("New prevision end date cannot be before the current prevision end date.");
        if (extensionRepository.countActiveByMemberId(loan.getMember().getId(), extendDate) >= loan.getMember().getSubscriptionType().getQuotaExtends())
            throw new IllegalStateException("Member has reached the extend quota for their subscription type.");
        loan.setPrevisionEndDate(newPrevisionEndDate);
        loan = loanRepository.save(loan);
        Extension extension = new Extension();
        extension.setLoan(loan);
        extension.setEndDate(loan.getEndDate());
        extensionRepository.save(extension);
        memberRepository.save(loan.getMember());
        return loanToLoanDTO.apply(loan);
    }

    public List<LoanDTO> getLoans() {
        return loanRepository.findLoansByIsReturnedFalse().stream()
                .map(loanToLoanDTO)
                .collect(Collectors.toList());
    }
}
