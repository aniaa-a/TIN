package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.CarDriver;

import java.util.List;
import java.util.Optional;

public class StandardCarDriverDao extends NamedParameterJdbcDaoSupport implements CarDriverDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardCarDriverDao.class);

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
