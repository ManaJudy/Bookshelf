package com.mana.bookshelf.converter.dtotoentity;

import com.mana.bookshelf.dto.ReservationDTO;
import com.mana.bookshelf.entity.BookCopy;
import com.mana.bookshelf.entity.LoanType;
import com.mana.bookshelf.entity.Member;
import com.mana.bookshelf.entity.Reservation;
import com.mana.bookshelf.exception.EntityNotFoundException;
import com.mana.bookshelf.repository.BookCopyRepository;
import com.mana.bookshelf.repository.BookRepository;
import com.mana.bookshelf.repository.LoanTypeRepository;
import com.mana.bookshelf.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReservationDTOToReservation implements Function<ReservationDTO, Reservation> {

    private final BookCopyRepository bookCopyRepository;
    private final MemberRepository memberRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final BookRepository bookRepository;

    public ReservationDTOToReservation(BookCopyRepository bookCopyRepository, MemberRepository memberRepository, LoanTypeRepository loanTypeRepository, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.memberRepository = memberRepository;
        this.loanTypeRepository = loanTypeRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Reservation apply(ReservationDTO reservationDTO) {
        if (reservationDTO == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        bookRepository.findById(reservationDTO.getBookId()).orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + reservationDTO.getBookId()));
        BookCopy bookCopy = bookCopyRepository.findAvailableCopiesByBookId(reservationDTO.getBookId(), reservationDTO.getReservationDate()).stream().findFirst().orElse(null);
        reservation.setBookCopy(bookCopy);
        Member member = memberRepository.findById(reservationDTO.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + reservationDTO.getMemberId()));
        reservation.setMember(member);
        LoanType loanType = loanTypeRepository.findById(reservationDTO.getLoanTypeId())
                .orElseThrow(() -> new EntityNotFoundException("LoanType not found with id: " + reservationDTO.getLoanTypeId()));
        reservation.setLoanType(loanType);
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setApproved(reservationDTO.isApproved());
        return reservation;
    }
}
