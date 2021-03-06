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
import pl.kosan.tin.model.Trip;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardTripDao extends NamedParameterJdbcDaoSupport implements TripDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardTripDao.class);

    private final static String FIND_BY_CITY = "SELECT id_trip, city, departure_time, arrive_time, content, photo, mini_photo, lead, title, embed_map FROM tin_trip WHERE city = :city";
    private final static String FIND_BY_ID = "SELECT id_trip, city, departure_time, arrive_time, " +
            "content,photo, lead, title, embed_map FROM tin_trip WHERE id_trip = :id";
    private final static String INSERT_TRIP = "INSERT INTO tin_trip(city, departure_time, arrive_time,content, photo, mini_photo, lead, title, embed_map) values( :city, :departureTime, :arriveTime, :content, :photo,:miniPhoto, :lead, :title, :embedMap)";
    private final static String FIND_ALL_TRIP = "SELECT id_trip, city, departure_time, arrive_time, content,  photo, mini_photo, lead, title, embed_map FROM tin_trip";
    private final static String INSERT_PRICES = "INSERT INTO tin_prices(id_trip, price) values(:idTrip, :price)";
    private final static String INSERT_SERVICE = "INSERT INTO tin_services(id_trip, service) values(:idTrip, :service)";

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    @Override
    public Long insertTrip(Trip trip) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("city", trip.getCity())
                .addValue("departureTime", trip.getDepartureTime())
                .addValue("arriveTime", trip.getArriveTime())
                .addValue("content", trip.getContent())
                .addValue("photo", trip.getPhoto())
                .addValue("lead", trip.getLead())
                .addValue("title", trip.getTitle())
                .addValue("miniPhoto", trip.getMiniPhoto())
                .addValue("embedMap", trip.getEmbedMap());


        try {
            getNamedParameterJdbcTemplate().update(INSERT_TRIP, mapSqlParameterSource, keyHolder);
            trip.setTripId((keyHolder.getKey().longValue()));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
        return keyHolder.getKey().longValue();

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
                        trip.setContent(rs.getString("content"));
                        trip.setLead(rs.getString("lead"));
                        trip.setPhoto(rs.getString("photo"));
                        trip.setTitle(rs.getString("title"));
                        trip.setEmbedMap(rs.getString("embed_map"));
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
                        trip.setContent(rs.getString("content"));
                        trip.setLead(rs.getString("lead"));
                        trip.setPhoto(rs.getString("photo"));
                        trip.setTitle(rs.getString("title"));
                        trip.setEmbedMap(rs.getString("embed_map"));
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
                        trip.setContent(rs.getString("content"));
                        trip.setLead(rs.getString("lead"));
                        trip.setPhoto(rs.getString("photo"));
                        trip.setMiniPhoto(rs.getString("mini_photo"));
                        trip.setTitle(rs.getString("title"));
                        trip.setEmbedMap(rs.getString("embed_map"));
                        return trip;
                    }));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void insertPrices(Double price, Long idTrip) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("price", price).addValue("idTrip", idTrip);
        try {
            getNamedParameterJdbcTemplate().update(INSERT_PRICES, mapSqlParameterSource, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void insertServices(String services, Long idTrip) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("service", services).addValue("idTrip", idTrip);
        try {
            getNamedParameterJdbcTemplate().update(INSERT_SERVICE, mapSqlParameterSource, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
