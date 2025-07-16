package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.entitytodto.BookToBookDTO;
import com.mana.bookshelf.converter.entitytodto.BookToBookDetailDTO;
import com.mana.bookshelf.dto.BookDTO;
import com.mana.bookshelf.dto.BookDetailDTO;
import com.mana.bookshelf.entity.Book;
import com.mana.bookshelf.exception.EntityNotFoundException;
import com.mana.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookToBookDTO bookToBookDTO;
    private final BookToBookDetailDTO bookToBookDetailDTO;

    public BookService(BookRepository bookRepository, BookToBookDTO bookToBookDTO, BookToBookDetailDTO bookToBookDetailDTO) {
        this.bookRepository = bookRepository;
        this.bookToBookDTO = bookToBookDTO;
        this.bookToBookDetailDTO = bookToBookDetailDTO;
    }

    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream().map(bookToBookDTO).toList();
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookToBookDTO.apply(book);
    }

    public List<BookDTO> getBooksByQuery(String query) {
        return bookRepository.findAll()
                .stream()
                .filter(b -> b.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        b.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .map(bookToBookDTO)
                .toList();
    }

    public BookDetailDTO getDetailById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookToBookDetailDTO.apply(book);
    }
}
