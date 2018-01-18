package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.ReservationDao;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.dao.UserDao;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.model.User;

@Service
public class StandardReservationService implements ReservationService {

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    UserDao userDao;

    @Autowired
    TripDao tripDao;


    @Override
    public void setReservation(UserReservationRespDto reservation) {

        Reservation res = new Reservation();

        Long userId = userDao.findUserByMail(reservation.getMailUser()).getIdUser();
        res.setUserId(userId);
        Long tripId = tripDao.findTripByCity(reservation.getCity()).getTripId();
        res.setTripId(tripId);
        res.setDateTrip(reservation.getDateTrip());
        res.setNumOfPeople(reservation.getNumPeople());
        res.setStatus(reservation.getStatus());

        reservationDao.insertReservation(res);
    }
}
