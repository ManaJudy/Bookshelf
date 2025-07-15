package com.mana.bookshelf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SubscriptionType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer quota;
}
