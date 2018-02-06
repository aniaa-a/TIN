package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kosan.tin.dto.CarDriverReservationRespDto;
import pl.kosan.tin.services.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/addCarDriver")
    public void addCarDriverToReservation(CarDriverReservationRespDto carDriverReservationRespDto){

        adminService.addCarDriverToReservation(carDriverReservationRespDto);
    }

}
