package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.dto.TripDto;
import pl.kosan.tin.model.Trip;
import pl.kosan.tin.services.ApplicationService;
import pl.kosan.tin.services.TripService;

import java.util.List;

@RestController
@RequestMapping(value = "/trip")
public class TripController {

    @Autowired
    TripService tripService;

    @Autowired
    ApplicationService applicationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<TripDto> getAllTrip() {
        return applicationService.getAllTrip();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public TripDto getTripById(@PathVariable("id") Long id) {

        return  applicationService.getTripById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void insertTrip(@RequestBody Trip trip) {

        tripService.insertTrip(trip);
    }


}
