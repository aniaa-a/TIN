package pl.kosan.tin.dao;

import pl.kosan.tin.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripDao {



    Long insertTrip(Trip rip);

    void updateTrip(Trip trip);

    void deleteTripById(Long TripId);

    Trip findTripByCity(String city);

    Trip findTripById(Long id);

    Optional<List<Trip>> findAllTrip();

    void insertPrices(Double prices,Long idTrip);
    void insertServices(String services,Long idTrip);
}
