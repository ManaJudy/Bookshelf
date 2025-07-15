package com.mana.bookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BookCopy {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
