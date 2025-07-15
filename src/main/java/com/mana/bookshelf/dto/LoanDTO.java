package com.mana.bookshelf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDTO {
    private Long id;
    private Long bookId;
    private Long memberId;
    private Long loanTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate previsionEndDate;
    private Boolean isReturned;
}
