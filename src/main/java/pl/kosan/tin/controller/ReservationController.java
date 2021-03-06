package pl.kosan.tin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.dto.ReservationPerUserDto;
import pl.kosan.tin.dto.ReservationRespDto;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.CarDriver;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.services.ReservationService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    ReservationService reservationService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void setReservation(@RequestBody UserReservationRespDto reservation) {

        reservationService.setReservation(reservation);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<ReservationRespDto> getAllReservation() {

        return reservationService.getAllReservations();

    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value= "/{id}")
    public void deleteReservation(@PathVariable("id") Long id) {

        reservationService.deleteReservationById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public List<ReservationPerUserDto> getReservationByUserId(@PathVariable("userId") Long userId) {

        return reservationService.findReservationByUser(userId);

    }


}
