package com.mana.bookshelf.converter.dtotoentity;

import com.mana.bookshelf.dto.BookDTO;
import com.mana.bookshelf.entity.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookDTOToBook implements Function<BookDTO, Book> {

    @Override
    public Book apply(BookDTO bookDTO) {
        if (bookDTO == null) return null;
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setAgeRating(bookDTO.getAgeRating());
        return book;
    }
}
