package pl.kosan.tin.dao;

import pl.kosan.tin.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {


    void insertCar(Car car);

    void updateCar(Car car);

    void deleteCarById(Long carId);

    Car findCarById(Long carId);

    Optional<List<Car>> findAllCar();
}
