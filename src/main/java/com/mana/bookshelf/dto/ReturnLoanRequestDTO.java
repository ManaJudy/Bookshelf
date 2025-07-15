package com.mana.bookshelf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnLoanRequestDTO {
    private LocalDate returnDate;
}
