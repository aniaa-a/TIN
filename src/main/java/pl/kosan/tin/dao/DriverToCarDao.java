package pl.kosan.tin.dao;

import pl.kosan.tin.model.DriverToCar;


public interface DriverToCarDao {

    void insertCar(DriverToCar car);

    void updateCar(DriverToCar car);

    void deleteCarById(Long carId);

    DriverToCar findDriverToCarById(Long carId);


}
