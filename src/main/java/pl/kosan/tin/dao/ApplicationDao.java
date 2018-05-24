package pl.kosan.tin.dao;

import pl.kosan.tin.dto.CarDriverReservationRespDto;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Price;
import pl.kosan.tin.model.Services;

import java.util.List;
import java.util.Optional;

public interface ApplicationDao {

    Optional<List<UserReservationRespDto>> findReservationForUser(String email);

    void addCarDriverToReservation(CarDriverReservationRespDto carDriverReservationRespDto);

    void updateReservation(Long idReservation, Long idCarToDriver);

    Optional<List<Price>> findPricesForTrip(Long idTrip);

    Optional<List<Services>> findServicesForTrip(Long idTrip);

}
