package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.dtotoentity.ReservationDTOToReservation;
import com.mana.bookshelf.converter.entitytodto.ReservationToReservationDTO;
import com.mana.bookshelf.dto.ReservationDTO;
import com.mana.bookshelf.entity.Reservation;
import com.mana.bookshelf.exception.EntityNotFoundException;
import com.mana.bookshelf.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationDTOToReservation reservationDTOToReservation;
    private final ReservationToReservationDTO reservationToReservationDTO;

    public ReservationService(ReservationRepository reservationRepository, ReservationDTOToReservation reservationDTOToReservation, ReservationToReservationDTO reservationToReservationDTO) {
        this.reservationRepository = reservationRepository;
        this.reservationDTOToReservation = reservationDTOToReservation;
        this.reservationToReservationDTO = reservationToReservationDTO;
    }

    public void verifyReservation(Reservation reservation) {
        if (reservation.getBookCopy() == null)
            throw new IllegalStateException("No available book copy for the reservation.");
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationDTOToReservation.apply(reservationDTO);
        reservation.setApproved(false);
        verifyReservation(reservation);
        reservation = reservationRepository.save(reservation);
        return reservationToReservationDTO.apply(reservation);
    }

    public ReservationDTO approveReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationId));
        if (reservation.isApproved())
            throw new IllegalStateException("Reservation with id " + reservationId + " has already been approved.");
        reservation.setApproved(true);
        reservation = reservationRepository.save(reservation);
        return reservationToReservationDTO.apply(reservation);
    }

    public void deleteReservation(Long reservationId) {
        if (!reservationRepository.existsById(reservationId))
            throw new EntityNotFoundException("Reservation with id " + reservationId + " does not exist.");
        reservationRepository.deleteById(reservationId);
    }
}
