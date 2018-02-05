package pl.kosan.tin.services;

import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.CarDriver;

public interface AdminService {

    void addCarDriverToReservation(Car car, CarDriver carDriver, Long idReservation);

}
