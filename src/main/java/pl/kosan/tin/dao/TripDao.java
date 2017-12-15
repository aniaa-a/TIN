package pl.kosan.tin.dao;

import pl.kosan.tin.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripDao {



    void insertTrip(Trip rip);

    void updateTrip(Trip trip);

    void deleteTripById(Long TripId);

    Trip findTripById(Long TripId);

    Optional<List<Trip>> findAllTrip();
}
