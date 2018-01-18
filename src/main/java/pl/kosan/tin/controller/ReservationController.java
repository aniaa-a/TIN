package pl.kosan.tin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.services.ReservationService;


@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    ReservationService reservationService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/book")
    public void setReservation(@RequestBody UserReservationRespDto reservation) {

        reservationService.setReservation(reservation);

    }




}
