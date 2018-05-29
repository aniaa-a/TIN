package pl.kosan.tin.services;

import pl.kosan.tin.dto.CarDriverReservationRespDto;
import pl.kosan.tin.dto.TripDto;

import java.util.List;

public interface ApplicationService {

    void addCarDriverToReservation(CarDriverReservationRespDto carDriverReservationRespDto);

    List<TripDto> getAllTrip();

    TripDto getTripById(Long tripId);
}
