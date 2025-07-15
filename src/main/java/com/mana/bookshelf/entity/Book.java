package com.mana.bookshelf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private Integer ageRating;

    @OneToMany(mappedBy = "book")
    private List<BookCopy> copies;
}
