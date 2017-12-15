package pl.kosan.tin.dao;

import pl.kosan.tin.model.CarDriver;
import java.util.List;
import java.util.Optional;

public interface CarDriverDao {


    void insertCar(CarDriver carDriver);

    void updateCar(CarDriver carDriver);

    void deleteCarById(Long carDriverId);

    CarDriver findCarById(Long carDriverId);

    Optional<List<CarDriver>> findAllCar();
}
