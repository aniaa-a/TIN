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
import pl.kosan.tin.model.ReservationStatus;
import pl.kosan.tin.model.Trip;
import pl.kosan.tin.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardTripDao extends NamedParameterJdbcDaoSupport implements TripDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardTripDao.class);

    private final static String FIND_BY_CITY = "SELECT id_trip, city, departure_time, arrive_time, content, price_person FROM tin_trip WHERE city = :city";
    private final static String FIND_BY_ID = "SELECT id_trip, city, departure_time, arrive_time, " +
            "content, price_person FROM tin_trip WHERE id_trip = :id";
    private final static String INSERT_TRIP = "INSERT INTO tin_trip(city, departure_time, arrive_time,price_person,content) values( :city, :departureTime, :arriveTime, :pricePerson, :content)";
    private final static String FIND_ALL_TRIP = "SELECT id_trip, city, departure_time, arrive_time, content, price_person FROM tin_trip";


    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    @Override
    public void insertTrip(Trip trip) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("city", trip.getCity())
                .addValue("departureTime", trip.getDepartureTime()).addValue("arriveTime", trip.getArriveTime())
                .addValue("pricePerson", trip.getPricePerPerson()).addValue("content", trip.getContent());

        try {
            getNamedParameterJdbcTemplate().update(INSERT_TRIP, mapSqlParameterSource, keyHolder);
            trip.setTripId((keyHolder.getKey().longValue()));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void updateTrip(Trip trip) {

    }

    @Override
    public void deleteTripById(Long TripId) {

    }

    @Override
    public Trip findTripByCity(String city) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("city", city);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(FIND_BY_CITY, mapSqlParameterSource,
                    (rs, rowNum) -> {
                        Trip trip = new Trip();
                        trip.setTripId(rs.getLong("id_trip"));
                        trip.setCity(rs.getString("city"));
                        trip.setDepartureTime(rs.getString("departure_time"));
                        trip.setArriveTime(rs.getString("arrive_time"));
                        trip.setPricePerPerson(rs.getDouble("price_person"));
                        trip.setContent(rs.getString("content"));
                        return trip;
                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public Trip findTripById(Long id) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(FIND_BY_ID, mapSqlParameterSource,
                    (rs, rowNum) -> {
                        Trip trip = new Trip();
                        trip.setTripId(rs.getLong("id_trip"));
                        trip.setCity(rs.getString("city"));
                        trip.setDepartureTime(rs.getString("departure_time"));
                        trip.setArriveTime(rs.getString("arrive_time"));
                        trip.setPricePerPerson(rs.getDouble("price_person"));
                        trip.setContent(rs.getString("content"));
                        return trip;
                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public Optional<List<Trip>> findAllTrip() {
        try {
            return Optional.ofNullable(getNamedParameterJdbcTemplate().query(FIND_ALL_TRIP,
                    new MapSqlParameterSource(), (rs, rowNum) -> {
                        Trip trip = new Trip();
                        trip.setTripId(rs.getLong("id_trip"));
                        trip.setCity(rs.getString("city"));
                        trip.setDepartureTime(rs.getString("departure_time"));
                        trip.setArriveTime(rs.getString("arrive_time"));
                        trip.setPricePerPerson(rs.getDouble("price_person"));
                        trip.setContent(rs.getString("content"));
                        return trip;
                    }));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
