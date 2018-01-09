package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.dto.UserReservationRespDto;

import java.util.List;
import java.util.Optional;

public class StandardApplicationDao extends NamedParameterJdbcDaoSupport implements ApplicationDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardApplicationDao.class);

    private static final String FIND_RESERVATION_FOR_USER = "SELECT a.id_reservation, b.name + b.surname driver, c.city, d.user_name, d.user_surname, d.identity_document, d.email, d.phone, a.date_trip, a.status, c.price_person, a.num_people\n" +
            "FROM tin_reservation a, tin_trip c, tin_carDriver b, tin_user d, tin_driver_to_car e\n" +
            "WHERE a.id_trip = c.id_trip AND a.id_user = d.id_user AND a.id_driver_to_car = e.id_driver_to_car AND b.id_carDriver = e.id_carDriver AND d.email = :email";


    @Override
    public Optional<List<UserReservationRespDto>> findReservationForUser(String email) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);
        try {

            return Optional.ofNullable(getNamedParameterJdbcTemplate().query(FIND_RESERVATION_FOR_USER, mapSqlParameterSource, (rs, rowNum) -> {
                UserReservationRespDto userReservationRespDto = new UserReservationRespDto();
                userReservationRespDto.setReservationId(rs.getLong("id_reservation"));
                userReservationRespDto.setCity(rs.getString("city"));
                userReservationRespDto.setDateTrip(rs.getDate("date_trip"));
                userReservationRespDto.setMailUser(rs.getString("email"));
                userReservationRespDto.setPhoneUser(rs.getString("phone"));
              //  userReservationRespDto.setPricePerPerson(rs.getDouble(""));
                return userReservationRespDto;

            }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }
}
