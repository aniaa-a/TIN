package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.dto.ReservationRespDto;
import pl.kosan.tin.dto.TripDto;
import pl.kosan.tin.model.Price;
import pl.kosan.tin.model.Services;
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
    public void insertTrip(TripDto tripDto) {
        Trip trip = new Trip();
        trip.setArriveTime(tripDto.getArriveTime());
        trip.setCity(tripDto.getCity());
        trip.setContent(tripDto.getContent());
        trip.setDepartureTime(tripDto.getDepartureTime());
        trip.setEmbedMap(tripDto.getEmbedMap());
        trip.setLead(tripDto.getLead());
        trip.setPhoto(tripDto.getPhoto());
        trip.setTitle(tripDto.getTitle());
        trip.setMiniPhoto(tripDto.getMiniPhoto());

        trip.setTripId(tripDao.insertTrip(trip));

        List<Double> prices = tripDto.getPrices();
        List<String> services = tripDto.getServices();
        for (Double s : prices) {
            tripDao.insertPrices(s, trip.getTripId());
        }
        for (String s : services) {
            tripDao.insertServices(s, trip.getTripId());
        }
    }
}
