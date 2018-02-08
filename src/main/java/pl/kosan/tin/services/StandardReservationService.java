package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.ReservationDao;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.dao.UserDao;
import pl.kosan.tin.dto.ReservationRespDto;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.model.User;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ReservationRespDto> getAllReservations() {

        List<ReservationRespDto> resList = new ArrayList<ReservationRespDto>();

        Optional<List<ReservationRespDto>> optListRes = reservationDao.findAllReservation();

        if (optListRes.isPresent()) {
            resList = optListRes.get();
        }


       return resList;
    }
}
