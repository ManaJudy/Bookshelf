package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.BookDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookToBookDTO implements Function<Book, BookDTO> {

    @Override
    public BookDTO apply(Book book) {
        if (book == null) return null;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setAgeRating(book.getAgeRating());
        return bookDTO;
    }
}
