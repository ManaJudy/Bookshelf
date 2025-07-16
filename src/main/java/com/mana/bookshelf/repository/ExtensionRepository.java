package com.mana.bookshelf.repository;

import com.mana.bookshelf.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    @Query("select count(e) from Extension e where e.loan.member.id = :memberId and (e.loan.isReturned = false or e.endDate >= :currentDate)")
    int countActiveByMemberId(Long memberId, LocalDate currentDate);
}
