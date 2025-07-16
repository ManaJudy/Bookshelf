package com.mana.bookshelf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDetailDTO {
    private Long id;
    private String name;
    private String subscription;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;
    private Integer quota;
    private LocalDate penalty;
}
