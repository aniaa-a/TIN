package pl.kosan.tin.services;

import pl.kosan.tin.dto.ReservationRespDto;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    void setReservation(UserReservationRespDto reservation);

    List<ReservationRespDto> getAllReservations();

    void deleteReservationById(Long reservationId);
}
