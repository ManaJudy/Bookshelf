package com.mana.bookshelf.dto;

import lombok.Data;

@Data
public class SubscriptionTypeDTO {
    private Long id;
    private String name;
    private Integer quota;
}
