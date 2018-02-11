package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.dto.ReservationRespDto;
import pl.kosan.tin.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StandardTripService implements TripService {

    @Autowired
    TripDao tripDao;

    @Override
    public Trip findTripByCity(String city) {
        return tripDao.findTripByCity(city);
    }

    @Override
    public Trip findTripById(Long id) {
        return tripDao.findTripById(id);
    }

    @Override
    public List<Trip> findallTrip() {

        List<Trip> tripList = new ArrayList<Trip>();

        Optional<List<Trip>> optListTrip = tripDao.findAllTrip();

        if (optListTrip.isPresent()) {
            tripList = optListTrip.get();
        }

        return tripList;

    }

    @Override
    public void insertTrip(Trip trip) {
        tripDao.insertTrip(trip);
    }
}
