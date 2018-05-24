package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kosan.tin.dto.CarResponseDto;
import pl.kosan.tin.services.CarService;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    @Autowired
    CarService carService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public CarResponseDto getAllCar() {

        CarResponseDto carResponseDto = new CarResponseDto();

        carResponseDto.setCarList(carService.findAllCar());

        return carResponseDto;
    }
}
