package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.model.Trip;
import pl.kosan.tin.services.TripService;

import java.util.List;

@RestController
@RequestMapping(value = "/trip")
public class TripController {

    @Autowired
    TripService tripService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<Trip> getAllTrip() {

        return tripService.findallTrip();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public Trip getTripById( @RequestParam(defaultValue = "id") Long id) {

        return tripService.findTripById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/addTrip")
    public void insertTrip(@RequestBody Trip trip){

        tripService.insertTrip(trip);
    }




}
