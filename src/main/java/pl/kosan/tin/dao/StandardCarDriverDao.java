package pl.kosan.tin.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.CarDriver;

import java.util.List;
import java.util.Optional;

public class StandardCarDriverDao extends NamedParameterJdbcDaoSupport implements CarDriverDao {
    @Override
    public void insertCar(CarDriver carDriver) {

    }

    @Override
    public void updateCar(CarDriver carDriver) {

    }

    @Override
    public void deleteCarById(Long carDriverId) {

    }

    @Override
    public CarDriver findCarById(Long carDriverId) {
        return null;
    }

    @Override
    public Optional<List<CarDriver>> findAllCar() {
        return Optional.empty();
    }
}
