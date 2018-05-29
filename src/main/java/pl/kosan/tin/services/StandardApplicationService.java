package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.ApplicationDao;
import pl.kosan.tin.dao.SliderDao;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.dto.CarDriverReservationRespDto;
import pl.kosan.tin.dto.TripDto;
import pl.kosan.tin.model.Price;
import pl.kosan.tin.model.Services;
import pl.kosan.tin.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StandardApplicationService implements ApplicationService {

    @Autowired
    ApplicationDao applicationDao;

    @Autowired
    TripDao tripDao;

    @Autowired
    SliderDao sliderDao;

    @Override
    public void addCarDriverToReservation(CarDriverReservationRespDto carDriverReservationRespDto) {

        applicationDao.addCarDriverToReservation(carDriverReservationRespDto);
    }

    @Override
    public List<TripDto> getAllTrip() {
        List<Trip> trips = new ArrayList<>();
        List<TripDto> tripsDto = new ArrayList<>();
        List<Price> prices = new ArrayList<>();
        List<Services> services = new ArrayList<>();

        Optional<List<Trip>> tripsOpt = tripDao.findAllTrip();
        if (tripsOpt.isPresent()) {
            trips = tripsOpt.get();
        }
        for (Trip trip : trips) {

            Optional<List<Price>> optPrice = applicationDao.findPricesForTrip(trip.getTripId());
            if (optPrice.isPresent()) {
                prices = optPrice.get();
            }
            TripDto tripDto = new TripDto();
            tripDto.setArriveTime(trip.getArriveTime());
            tripDto.setCity(trip.getCity());
            tripDto.setContent(trip.getContent());
            tripDto.setDepartureTime(trip.getDepartureTime());
            tripDto.setLead(trip.getLead());
            tripDto.setPhoto(trip.getPhoto());
            tripDto.setTitle(trip.getTitle());
            tripDto.setTripId(trip.getTripId());
            tripDto.setMiniPhoto(trip.getMiniPhoto());
            tripDto.setEmbedMap(trip.getEmbedMap());

            tripDto.setPrices(prices.stream().map(price -> new Double(price.getPrice())).collect(Collectors.toList()));

            Optional<List<Services>> serviceopt = applicationDao.findServicesForTrip(trip.getTripId());
            if (serviceopt.isPresent()) {
                services = serviceopt.get();
            }
            tripDto.setServices(services.stream().map(serv -> new String(serv.getService())).collect(Collectors.toList()));
            tripsDto.add(tripDto);
        }
        return tripsDto;
    }

    @Override
    public TripDto getTripById(Long tripId) {
        TripDto tripDto = new TripDto();
        List<Price> prices = new ArrayList<>();
        List<Services> services = new ArrayList<>();

        Trip trip= tripDao.findTripById(tripId);

            Optional<List<Price>> optPrice = applicationDao.findPricesForTrip(tripId);
            if (optPrice.isPresent()) {
                prices = optPrice.get();
            }
            tripDto = new TripDto();
            tripDto.setArriveTime(trip.getArriveTime());
            tripDto.setCity(trip.getCity());
            tripDto.setContent(trip.getContent());
            tripDto.setDepartureTime(trip.getDepartureTime());
            tripDto.setLead(trip.getLead());
            tripDto.setPhoto(trip.getPhoto());
            tripDto.setTitle(trip.getTitle());
            tripDto.setTripId(trip.getTripId());
            tripDto.setMiniPhoto(trip.getMiniPhoto());
            tripDto.setEmbedMap(trip.getEmbedMap());

            tripDto.setPrices(prices.stream().map(price -> new Double(price.getPrice())).collect(Collectors.toList()));

            Optional<List<Services>> serviceopt = applicationDao.findServicesForTrip(trip.getTripId());
            if (serviceopt.isPresent()) {
                services = serviceopt.get();
            }
            tripDto.setServices(services.stream().map(serv -> new String(serv.getService())).collect(Collectors.toList()));
        return tripDto;
    }


}
