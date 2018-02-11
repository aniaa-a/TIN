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
import pl.kosan.tin.dto.ReservationPerUserDto;
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

    private final static String FIND_ALL_RESERVATION = "select a.id_reservation, c.city, a.date_trip, a.status, b.id_car, b.id_cardriver, d.email " +
            "from  tin_driver_to_car b right join tin_reservation a on  a.id_driver_to_car = b.id_driver_to_car\n" +
            "join tin_trip c on a.id_trip = c.id_trip join tin_user d on a.id_user = d.id_user";

    private final static String DELETE_RESERVATION = "delete from tin_reservation where id_reservation = :idReservation";

    private final static String FIND_RESERVATION_BY_USER ="select a.id_reservation, b.city, a.date_trip, a.status, a.num_people, b.price from tin_reservation a , tin_trip b where a.id_trip = b.id_trip and  id_user = :id";

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
    public void deleteReservationById(Long reservationId) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idReservation", reservationId);
        LOGGER.info("id rezerwacji:  "+reservationId);
        try {
            getNamedParameterJdbcTemplate().update(DELETE_RESERVATION, mapSqlParameterSource);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public Reservation findReservationById(Long reservationId) {
        return null;
    }

    @Override
    public Optional<List<ReservationPerUserDto>> findReservationByUser(Long userId) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);
        try {
            return Optional.ofNullable(getNamedParameterJdbcTemplate().query(FIND_RESERVATION_BY_USER,
                    mapSqlParameterSource, (rs, rowNum) -> {
                        ReservationPerUserDto reservationPerUserDto = new ReservationPerUserDto();
                        reservationPerUserDto.setIdReservation(rs.getLong("id_reservation"));
                        reservationPerUserDto.setCity(rs.getString("city"));
                        reservationPerUserDto.setDateTrip(rs.getString("date_trip"));
                        reservationPerUserDto.setNumPeople(rs.getInt("num_people"));
                        reservationPerUserDto.setPricePerPerson(rs.getString("price_person"));
                        return reservationPerUserDto;

                    }));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
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
                        reservationRespDto.setStatus((ReservationStatus) rs.getObject("status"));
                        reservationRespDto.setCarId(rs.getLong("id_car"));
                        reservationRespDto.setDriverId(rs.getLong("id_cardriver"));
                        reservationRespDto.setEmail(rs.getString("email"));
                        return reservationRespDto;
                    }));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }


}
