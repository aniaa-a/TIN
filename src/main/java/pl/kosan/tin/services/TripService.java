package pl.kosan.tin.services;

import pl.kosan.tin.dto.TripDto;
import pl.kosan.tin.model.Trip;

import java.util.List;

public interface TripService {


    Trip findTripByCity(String city);
    Trip findTripById(Long id);
    List<Trip> findallTrip();
    void insertTrip(TripDto trip);



}
