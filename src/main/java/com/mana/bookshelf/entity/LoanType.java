package com.mana.bookshelf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LoanType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer maxLoanDays;
}
