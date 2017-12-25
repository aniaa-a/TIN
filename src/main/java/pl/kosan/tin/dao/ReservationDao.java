package pl.kosan.tin.dao;

import pl.kosan.tin.model.DriverToCar;
import pl.kosan.tin.model.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationDao {


    void insertReservation(Reservation reservation);

    void updateReservation(Reservation reservation);

    void deleteReservationById(Long reservationId);

    Reservation findReservationById(Long reservationId);

    Optional<List<Reservation>> findAllReservation();

    Optional<List<Reservation>> findReservationByDate( Date dateTrip);

    void addCarDriver(DriverToCar driverToCar);


}
