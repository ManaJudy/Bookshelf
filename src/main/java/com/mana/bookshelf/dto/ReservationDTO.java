package com.mana.bookshelf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {
    private Long id;
    private Long bookId;
    private Long memberId;
    private Long loanTypeId;
    private LocalDate reservationDate;
    private Boolean approved;
}
