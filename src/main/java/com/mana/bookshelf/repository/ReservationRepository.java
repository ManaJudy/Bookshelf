package com.mana.bookshelf.repository;

import com.mana.bookshelf.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    int countByMemberIdAndIsApprovedTrue(Long memberId);
}
