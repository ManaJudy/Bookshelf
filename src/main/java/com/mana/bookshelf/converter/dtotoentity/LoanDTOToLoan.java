package com.mana.bookshelf.converter.dtotoentity;

import com.mana.bookshelf.dto.LoanDTO;
import com.mana.bookshelf.entity.BookCopy;
import com.mana.bookshelf.entity.Loan;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.entity.LoanType;
import com.mana.bookshelf.exception.EntityNotFoundException;
import com.mana.bookshelf.repository.BookCopyRepository;
import com.mana.bookshelf.repository.BookRepository;
import com.mana.bookshelf.repository.MemberRepository;
import com.mana.bookshelf.repository.LoanTypeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;

@Component
public class LoanDTOToLoan implements Function<LoanDTO, Loan> {

    private final BookCopyRepository bookCopyRepository;
    private final MemberRepository memberRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final BookRepository bookRepository;

    public LoanDTOToLoan(BookCopyRepository bookCopyRepository, MemberRepository memberRepository, LoanTypeRepository loanTypeRepository, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.memberRepository = memberRepository;
        this.loanTypeRepository = loanTypeRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Loan apply(LoanDTO loanDTO) {
        if (loanDTO == null) return null;
        LocalDate startDate = loanDTO.getStartDate() != null ? loanDTO.getStartDate() : LocalDate.now();
        Loan loan = new Loan();
        loan.setId(loanDTO.getId());
        bookRepository.findById(loanDTO.getBookId()).orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + loanDTO.getBookId()));
        BookCopy bookCopy = bookCopyRepository.findAvailableCopyByBookId(loanDTO.getBookId(), startDate).orElse(null);
        loan.setBookCopy(bookCopy);
        Member member = memberRepository.findById(loanDTO.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + loanDTO.getMemberId()));
        loan.setMember(member);
        LoanType loanType = loanTypeRepository.findById(loanDTO.getLoanTypeId())
                .orElseThrow(() -> new EntityNotFoundException("LoanType not found with id: " + loanDTO.getLoanTypeId()));
        loan.setLoanType(loanType);
        loan.setStartDate(startDate);
        loan.setEndDate(loanDTO.getEndDate());
        loan.setIsReturned(loanDTO.getIsReturned());
        return loan;
    }
}
