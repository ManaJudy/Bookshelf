package com.mana.bookshelf.repository;

import com.mana.bookshelf.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("select count(l) from Loan l where l.member.id = :memberId and (l.endDate > :currentDate or l.isReturned = false)")
    int countByMemberIdAndIsReturnedFalse(Long memberId, LocalDate currentDate);

    List<Loan> findLoansByIsReturnedFalse();
}
