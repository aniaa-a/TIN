package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kosan.tin.dao.CarDao;
import pl.kosan.tin.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StandardCarService implements CarService {

    @Autowired
    CarDao carDao;

    @Override
    public List<Car> findAllCar() {

        List<Car> carList = new ArrayList<Car>();

        Optional<List<Car>> optListCar = carDao.findAllCar();

        if (optListCar.isPresent()) {
            carList = optListCar.get();
        }

        return carList;

    }
}
