package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class StandardReservationDao extends NamedParameterJdbcDaoSupport implements ReservationDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardReservationDao.class);

    @Override
    public void insertReservation(Reservation reservation) {

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
}
