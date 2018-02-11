package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.kosan.tin.dto.CarDriverReservationRespDto;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.CarDriver;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardAdminDao extends NamedParameterJdbcDaoSupport implements AdminDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardAdminDao.class);

    private static final String FIND_RESERVATION_FOR_USER = "SELECT a.id_reservation, b.name + b.surname driver, c.city, d.user_name, d.user_surname, d.identity_document, d.email, d.phone, a.date_trip, a.status, c.price_person, a.num_people\n" +
            "FROM tin_reservation a, tin_trip c, tin_carDriver b, tin_user d, tin_driver_to_car e\n" +
            "WHERE a.id_trip = c.id_trip AND a.id_user = d.id_user AND a.id_driver_to_car = e.id_driver_to_car AND b.id_carDriver = e.id_carDriver AND d.email = :email";

    private static final String ADD_CAR_DRIVER = "insert into tin_driver_to_car (id_cardriver, id_car, date_trip) \n" +
            "values(:id_cardriver, :id_car, :date_trip)";

    private final static String UPDATE_RESERVATION = "update tin_reservation set id_driver_to_car = :id_driver_to_car where id_reservation = :id_reservation";

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public Optional<List<UserReservationRespDto>> findReservationForUser(String email) {

        return null;
    }


    @Override
    public void addCarDriverToReservation(CarDriverReservationRespDto carDriverReservationRespDto) {

        Long id;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id_cardriver", carDriverReservationRespDto.getIdCarDriver())
                .addValue("id_car", carDriverReservationRespDto.getIdCar())
                .addValue("date_trip", carDriverReservationRespDto.getDate());

        try {
            getNamedParameterJdbcTemplate().update(ADD_CAR_DRIVER, mapSqlParameterSource, keyHolder);
            id = (keyHolder.getKey().longValue());

              updateReservation(carDriverReservationRespDto.getIdReservation(), id);
              LOGGER.info("idres: "+carDriverReservationRespDto.getIdReservation());
              LOGGER.info("id : "+id);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void updateReservation(Long idReservation, Long idCarToDriver) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id_driver_to_car", idCarToDriver)
                .addValue("id_reservation", idReservation );

        try {
            getNamedParameterJdbcTemplate().update(UPDATE_RESERVATION, mapSqlParameterSource);

        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }
}