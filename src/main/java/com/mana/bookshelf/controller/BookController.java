package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.BookDTO;
import com.mana.bookshelf.dto.BookDetailDTO;
import com.mana.bookshelf.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(@RequestParam (required = false) String query) {
        List<BookDTO> books = query == null ? bookService.getBooks() : bookService.getBooksByQuery(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<BookDetailDTO> getBookDetailById(@PathVariable Long id) {
        BookDetailDTO bookDetail = bookService.getDetailById(id);
        return new ResponseEntity<>(bookDetail, HttpStatus.OK);
    }
}
