package com.mana.bookshelf.dto;

import lombok.Data;

@Data
public class SubscriptionTypeDTO {
    private Long id;
    private String name;
    private Integer quota;
    private Integer quotaLoans;
    private Integer quotaReservations;
    private Integer quotaExtends;
    private Integer penaltyDays;
    private Integer maxLoanDays;
}
