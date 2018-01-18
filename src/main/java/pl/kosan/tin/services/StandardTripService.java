package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.model.Trip;

@Service
public class StandardTripService implements TripService {

    @Autowired
    TripDao tripDao;

    @Override
    public Trip findTripByCity(String city) {
        return tripDao.findTripByCity(city);
    }
}
