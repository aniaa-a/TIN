package pl.kosan.tin.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.Trip;

import java.util.List;
import java.util.Optional;

public class StandardTripDao extends NamedParameterJdbcDaoSupport implements TripDao{


    @Override
    public void insertTrip(Trip rip) {

    }

    @Override
    public void updateTrip(Trip trip) {

    }

    @Override
    public void deleteTripById(Long TripId) {

    }

    @Override
    public Trip findTripById(Long TripId) {
        return null;
    }

    @Override
    public Optional<List<Trip>> findAllTrip() {
        return Optional.empty();
    }
}
