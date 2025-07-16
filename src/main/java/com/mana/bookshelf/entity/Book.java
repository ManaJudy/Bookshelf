package com.mana.bookshelf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Integer ageRating;

    @OneToMany(mappedBy = "book")
    private List<BookCopy> copies;
}