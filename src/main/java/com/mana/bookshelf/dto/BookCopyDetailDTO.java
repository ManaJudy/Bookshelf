package com.mana.bookshelf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookCopyDetailDTO {
    private Long id;
    private boolean isAvailable;
    private LocalDate previsionAvailabilityDate;
}
