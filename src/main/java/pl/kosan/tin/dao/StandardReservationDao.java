package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.kosan.tin.dto.ReservationRespDto;
import pl.kosan.tin.model.CarDriver;
import pl.kosan.tin.model.DriverToCar;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.model.ReservationStatus;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardReservationDao extends NamedParameterJdbcDaoSupport implements ReservationDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardReservationDao.class);

    private final static String INSERT_RESERVATION = "INSERT INTO tin_reservation(id_trip, id_user, date_trip, status, num_people)" +
            "VALUES(:id_trip, :id_user, :date_trip, :status, :num_people)";

    private final static String FIND_ALL_RESERVATION = "select a.id_reservation, e.city, a.date_trip, a.status, b.registration_num, c.surname, c.pesel\n" +
            "from  tin_cardriver c join tin_driver_to_car d  on c.id_cardriver = d.id_cardriver\n" +
            "join tin_car b on b.id_car = d.id_car\n" +
            "right join tin_reservation a on  a.id_driver_to_car = d.id_driver_to_car \n" +
            "join tin_trip e on a.id_trip = e.id_trip";

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
                .addValue("num_people", reservation.getNumOfPeople());
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
    public Optional<List<ReservationRespDto>> findAllReservation() {
        try {
            return Optional.ofNullable(getNamedParameterJdbcTemplate().query(FIND_ALL_RESERVATION,
                    new MapSqlParameterSource(), (rs, rowNum) -> {

                        ReservationRespDto reservationRespDto = new ReservationRespDto();
                        reservationRespDto.setIdReservation(rs.getLong( "id_reservation"));
                        reservationRespDto.setCity(rs.getString("city"));
                        reservationRespDto.setDateTrip(rs.getDate("date_trip"));
                        reservationRespDto.setPeselClient(rs.getString("pesel"));
                        reservationRespDto.setRegistration_num(rs.getString("registration_num"));
                        reservationRespDto.setStatus((ReservationStatus) rs.getObject("status"));
                        reservationRespDto.setSurnameClient(rs.getString("surname"));

                        return reservationRespDto;
                    }));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<List<Reservation>> findReservationByDate(Date dateTrip) {
        return Optional.empty();
    }

    @Override
    public void addCarDriver(DriverToCar driverToCar) {


    }
}
