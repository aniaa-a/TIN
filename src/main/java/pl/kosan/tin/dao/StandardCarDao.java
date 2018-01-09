package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.CarDriver;
import pl.kosan.tin.model.User;

import java.util.List;
import java.util.Optional;

public class StandardCarDao extends NamedParameterJdbcDaoSupport implements CarDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardCarDao.class);


    private final static String FIND_CAR_BY_REGISTRATION_NUM= "SELECT id_car, brand, registration_num, car_seats from tin_car WHERE registration_num = :registration_num";
    private final static String FIND_ALL_CAR = "SELECT id_car, brand, registration_num, car_seats from tin_car";

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


        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("registration_num", registrationNum);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(FIND_CAR_BY_REGISTRATION_NUM, mapSqlParameterSource,
                    (rs, rowNum) -> {
                       Car car = new Car();
                       car.setCarId(rs.getLong("id_car"));
                       car.setBrand(rs.getString("brand"));
                       car.setRegistrationNum(rs.getString("registration_num"));
                       car.setCarSeats(rs.getInt("car_seats"));
                       return car;

                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<List<Car>> findAllCar() {
        try {
            return Optional.ofNullable(getNamedParameterJdbcTemplate().query(FIND_ALL_CAR,
                    new MapSqlParameterSource(), (rs, rowNum) -> {
                        Car car = new Car();
                        car.setCarId(rs.getLong("id_car"));
                        car.setBrand(rs.getString("brand"));
                        car.setRegistrationNum(rs.getString("registration_num"));
                        car.setCarSeats(rs.getInt("car_seats"));
                        return car;
                    }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
