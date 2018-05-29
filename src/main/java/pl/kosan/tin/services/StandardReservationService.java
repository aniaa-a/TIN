package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.ReservationDao;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.dao.UserDao;
import pl.kosan.tin.dto.ReservationPerUserDto;
import pl.kosan.tin.dto.ReservationRespDto;
import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.model.ReservationStatus;
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
        res.setTripId(reservation.getTripId());
        res.setDateTrip(reservation.getDateTrip());
        res.setNumOfPeople(reservation.getNumPeople());
        res.setPrice(reservation.getPrice());
        res.setStatus((ReservationStatus.Zarezerwowana).toString());

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

    @Override
    public void deleteReservationById(Long reservationId){

        reservationDao.deleteReservationById(reservationId);
    }

    @Override
    public List<ReservationPerUserDto> findReservationByUser(Long userId){

        List<ReservationPerUserDto> resList = new ArrayList<ReservationPerUserDto>();

        Optional<List<ReservationPerUserDto>> optListRes = reservationDao.findReservationByUser(userId);

        if (optListRes.isPresent()) {
            resList = optListRes.get();
        }


        return resList;
    }
}
