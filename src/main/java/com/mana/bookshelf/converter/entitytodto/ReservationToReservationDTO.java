package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.ReservationDTO;
import com.mana.bookshelf.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReservationToReservationDTO implements Function<Reservation, ReservationDTO> {

    @Override
    public ReservationDTO apply(Reservation reservation) {
        if (reservation == null) return null;
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setBookId(reservation.getBookCopy().getBook().getId());
        reservationDTO.setMemberId(reservation.getMember().getId());
        reservationDTO.setLoanTypeId(reservation.getLoanType().getId());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setApproved(reservation.isApproved());
        return reservationDTO;
    }
}
