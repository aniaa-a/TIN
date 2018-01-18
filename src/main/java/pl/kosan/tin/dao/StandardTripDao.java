package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pl.kosan.tin.model.Trip;
import pl.kosan.tin.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardTripDao extends NamedParameterJdbcDaoSupport implements TripDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardTripDao.class);

    private final static String FIND_BY_CITY = "SELECT id_trip, city, departure_time, arrive_time, price_person FROM tin_trip WHERE city = :city";

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    @Override
    public void insertTrip(Trip rip) {

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
        return Optional.empty();
    }
}
