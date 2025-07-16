package com.mana.bookshelf.dto;

import lombok.Data;

@Data
public class LoanTypeDTO {
    private Long id;
    private String name;
    private Integer maxLoanDays;
}
