package pl.kosan.tin.dao;

import pl.kosan.tin.model.DriverToCar;

import java.util.List;
import java.util.Optional;

public interface DriverToCarDao {

    void insertCar(DriverToCar car);

    void updateCar(DriverToCar car);

    void deleteCarById(Long carId);

    DriverToCar findDriverToCarById(Long carId);


}
