package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.ReservationDao;
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

    @Override
    public void setReservation(UserReservationRespDto reservation) {

        Reservation res = new Reservation();
        User user = userDao.findUserByMail(reservation.getMailUser());
        Trip trip
        res.setUserId(user.getIdUser());


        reservationDao.insertReservation(res);
    }
}
