package com.mana.bookshelf.repository;

import com.mana.bookshelf.entity.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    @Query("select bk from BookCopy bk where bk.book.id = :bookId " +
            "and bk not in (select l.bookCopy from Loan l where l.isReturned = false or l.endDate > :currentDate) " +
            "and bk not in (select r.bookCopy from Reservation r where r.approved = true ) ")
    Optional<BookCopy> findAvailableCopyByBookId(Long bookId, LocalDate currentDate);

    @Query("select bk from BookCopy bk where bk.book.id = :bookId " +
            "and bk not in (select l.bookCopy from Loan l where l.isReturned = false or l.endDate > :currentDate) " +
            "and bk not in (select r.bookCopy from Reservation r where r.approved = true ) ")
    List<BookCopy> findAvailableCopiesByBookId(Long bookId, LocalDate currentDate);
}
