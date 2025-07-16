package com.mana.bookshelf.repository;

import com.mana.bookshelf.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
