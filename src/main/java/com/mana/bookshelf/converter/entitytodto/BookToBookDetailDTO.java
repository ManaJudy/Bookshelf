package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.BookCopyDetailDTO;
import com.mana.bookshelf.dto.BookDetailDTO;
import com.mana.bookshelf.repository.BookCopyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Component
public class BookToBookDetailDTO implements Function<Book, BookDetailDTO> {
    private final BookCopyRepository bookCopyRepository;

    public BookToBookDetailDTO(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    @Override
    public BookDetailDTO apply(Book book) {
        if (book == null) return null;
        BookDetailDTO bookDetailDTO = new BookDetailDTO();
        bookDetailDTO.setId(book.getId());
        bookDetailDTO.setTitle(book.getTitle());
        bookDetailDTO.setAuthor(book.getAuthor());
        bookDetailDTO.setCopyCount(book.getCopies().size());
        List<BookCopy> availableCopies = bookCopyRepository.findAvailableCopiesByBookId(book.getId(), LocalDate.now());
        List<BookCopyDetailDTO> bookCopyDetailDTOs = book.getCopies().stream().map(c -> {
            BookCopyDetailDTO bookCopyDetailDTO = new BookCopyDetailDTO();
            bookCopyDetailDTO.setId(c.getId());
            bookCopyDetailDTO.setAvailable(availableCopies.contains(c));
            if (bookCopyDetailDTO.isAvailable())
                bookCopyDetailDTO.setPrevisionAvailabilityDate(c.getLoans().getLast().getPrevisionEndDate());
            return bookCopyDetailDTO;
        }).toList();
        bookDetailDTO.setCopies(bookCopyDetailDTOs);
        return bookDetailDTO;
    }
}
