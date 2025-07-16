package com.mana.bookshelf.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDetailDTO {
    private Long id;
    private String title;
    private String author;
    private Integer copyCount;
    private List<BookCopyDetailDTO> copies;
}
