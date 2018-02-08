package pl.kosan.tin.services;

import pl.kosan.tin.dto.CarDriverReservationRespDto;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.CarDriver;

import java.util.Date;

public interface AdminService {

    void addCarDriverToReservation(CarDriverReservationRespDto carDriverReservationRespDto);

    //void updateReservation(Long idReservation, Long idCarToDriver);

}
