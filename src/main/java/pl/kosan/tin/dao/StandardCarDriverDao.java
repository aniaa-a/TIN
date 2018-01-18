package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.CarDriver;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardCarDriverDao extends NamedParameterJdbcDaoSupport implements CarDriverDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardCarDriverDao.class);

    private final static String FIND_DRIVER_BY_PESEL = "SELECT id_cardriver, name, surname, pesel , identity_document, salary " +
            "FROM tin_cardriver WHERE pesel = :pesel";

    private final static String FIND_ALL_DRIVER = "SELECT id_cardriver, name, surname, pesel , identity_document, salary FROM tin_cardriver";

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

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
    public CarDriver findDriverByPesel(String pesel){


        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("pesel", pesel);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(FIND_DRIVER_BY_PESEL, mapSqlParameterSource,
                    (rs, rowNum) -> {
                        CarDriver driver = new CarDriver();
                        driver.setCarDriverId(rs.getLong("id_cardriver"));
                        driver.setName(rs.getString("name"));
                        driver.setSurname(rs.getString("surname"));
                        driver.setPesel(rs.getString("pesel"));
                        driver.setIdentityNum(rs.getString("identity_document"));
                        driver.setSalary(rs.getDouble("salary"));
                        return driver;

                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<List<CarDriver>> findAllCarDriver() {
        try {
            return Optional.ofNullable(getNamedParameterJdbcTemplate().query(FIND_ALL_DRIVER,
                    new MapSqlParameterSource(), (rs, rowNum) -> {
                        CarDriver driver = new CarDriver();
                        driver.setCarDriverId(rs.getLong("id_cardriver"));
                        driver.setName(rs.getString("name"));
                        driver.setSurname(rs.getString("surname"));
                        driver.setPesel(rs.getString("pesel"));
                        driver.setIdentityNum(rs.getString("identity_document"));
                        driver.setSalary(rs.getDouble("salary"));
                        return driver;
                    }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

}
