package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.LoanDTO;
import com.mana.bookshelf.entity.Loan;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LoanToLoanDTO implements Function<Loan, LoanDTO> {

    @Override
    public LoanDTO apply(Loan loan) {
        if (loan == null) return null;
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setBookId(loan.getBookCopy().getBook().getId());
        loanDTO.setMemberId(loan.getMember().getId());
        loanDTO.setStartDate(loan.getStartDate());
        loanDTO.setEndDate(loan.getEndDate());
        loanDTO.setIsReturned(loan.getIsReturned());
        loanDTO.setLoanTypeId(loan.getLoanType().getId());
        loanDTO.setPrevisionEndDate(loan.getPrevisionEndDate());
        return loanDTO;
    }
}
