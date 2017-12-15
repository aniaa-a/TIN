package pl.kosan.tin.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.DriverToCar;

public class StandardDriverToCarDao extends NamedParameterJdbcDaoSupport implements DriverToCarDao {
    @Override
    public void insertCar(DriverToCar car) {

    }

    @Override
    public void updateCar(DriverToCar car) {

    }

    @Override
    public void deleteCarById(Long carId) {

    }

    @Override
    public DriverToCar findDriverToCarById(Long carId) {
        return null;
    }
}
