package pl.kosan.tin.dao;

import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.CarDriver;
import pl.kosan.tin.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface AdminDao {

    Optional<List<UserReservationRespDto>> findReservationForUser(String email);

    void addCarDriverToReservation(Car car, CarDriver carDriver, Long idReservation);



}
