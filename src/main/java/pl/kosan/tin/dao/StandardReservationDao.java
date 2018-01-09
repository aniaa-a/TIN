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
import pl.kosan.tin.model.DriverToCar;
import pl.kosan.tin.model.Reservation;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardReservationDao extends NamedParameterJdbcDaoSupport implements ReservationDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardReservationDao.class);

    private final static String INSERT_RESERVATION = "INSERT INTO tin_reservation(id_trip, id_user, date_trip, status, numb_people)" +
            "VALUES(:id_trip, :id_user, :date_trip, :status, :numb_people)";

   // private final static String ADD_CARDRIVER = ""

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void insertReservation(Reservation reservation) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id_trip", reservation.getTripId())
                .addValue("id_user", reservation.getUserId()).addValue("date_trip", reservation.getDateTrip())
                .addValue("status", reservation.getStatus())
                .addValue("numb_people", reservation.getNumOfPeople());
        try {
            getNamedParameterJdbcTemplate().update(INSERT_RESERVATION, mapSqlParameterSource, keyHolder);
            reservation.setReservationId(keyHolder.getKey().longValue());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void updateReservation(Reservation reservation) {

    }

    @Override
    public void deleteReservationById(Long reservationId) {

    }

    @Override
    public Reservation findReservationById(Long reservationId) {
        return null;
    }

    @Override
    public Optional<List<Reservation>> findAllReservation() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Reservation>> findReservationByDate(Date dateTrip) {
        return Optional.empty();
    }

    @Override
    public void addCarDriver(DriverToCar driverToCar){


    }
}
