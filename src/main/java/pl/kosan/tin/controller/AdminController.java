package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.dto.CarDriverReservationRespDto;
import pl.kosan.tin.services.ApplicationService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    ApplicationService applicationService;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/addCarDriver")
    public void addCarDriverToReservation(@RequestBody CarDriverReservationRespDto carDriverReservationRespDto){

        applicationService.addCarDriverToReservation(carDriverReservationRespDto);

    }

}
