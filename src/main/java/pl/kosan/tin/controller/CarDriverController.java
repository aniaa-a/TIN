package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kosan.tin.dto.DriversResponseDto;
import pl.kosan.tin.services.DriverService;

@RestController
@RequestMapping(value = "/driver")
public class CarDriverController {


    @Autowired
    DriverService driverService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public DriversResponseDto getAllDriver() {

        DriversResponseDto driversResponseDto = new DriversResponseDto();

        driversResponseDto.setListCarDriver(driverService.findAllCarDriver());

        return driversResponseDto;
    }

}
