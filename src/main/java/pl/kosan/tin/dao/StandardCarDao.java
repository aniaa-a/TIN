package pl.kosan.tin.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.Car;

import java.util.List;
import java.util.Optional;

public class StandardCarDao extends NamedParameterJdbcDaoSupport implements CarDao {
    @Override
    public void insertCar(Car car) {

    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCarById(Long carId) {

    }

    @Override
    public Car findCarById(Long carId) {
        return null;
    }

    @Override
    public Car findCarByRegistrationNum(String registrationNum) {
        return null;
    }

    @Override
    public Optional<List<Car>> findAllCar() {
        return Optional.empty();
    }
}
