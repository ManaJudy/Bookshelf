package com.mana.bookshelf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExtendLoanRequestDTO {
    private LocalDate extendDate;
}
