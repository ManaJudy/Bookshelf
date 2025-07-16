package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.ReservationDTO;
import com.mana.bookshelf.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("/create")
    public String showCreateReservationForm() {
        return "reservations/create_reservation"; // Assuming you have a Thymeleaf template for creating reservations
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ReservationDTO> approveReservation(@PathVariable Long id) {
        ReservationDTO approvedReservation = reservationService.approveReservation(id);
        return new ResponseEntity<>(approvedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
