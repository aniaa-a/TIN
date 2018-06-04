package pl.kosan.tin.dao;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kosan.tin.dto.ReservationPerUserDto;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Ignore
public class StandardReservationDaoTest {

    @Autowired
    UserDao userDao;

    @Autowired
    ReservationDao reservationDao;

    Reservation reservation = new Reservation();
    User userId;


    @Before
    public void init() {

        User user = new User();
        user.setName("test");
        user.setSurname("test");
        user.setIdentityNum("car234343");
        user.setPhone("658736587435");
        user.setEmail("test@testDaoReservation.pl");
        user.setPassword("haslo");

        userDao.insertUser(user);
        userId = userDao.findUserByMail("test@testDaoReservation.pl");

        reservation.setDateTrip(new Date(2018,9,06));
        reservation.setNumOfPeople(5);
        reservation.setTripId(5L);
        reservation.setPrice(500.00);
        reservation.setUserId(userId.getIdUser());
    }

    @Test
    public void insertReservation() {

        reservationDao.insertReservation(reservation);
        Assert.assertNotNull(reservationDao.findReservationByUser(userId.getIdUser()));
    }

    @Test
    public void findAndDeleteReservationById() {
        List<ReservationPerUserDto> listReservation = new ArrayList<ReservationPerUserDto>();


        Optional<List<ReservationPerUserDto>> res = reservationDao.findReservationByUser(userId.getIdUser());
        if (res.isPresent()){
            listReservation = res.get();
        }
        Long idReservation;
        if(listReservation.size()>0) {
            idReservation = listReservation.get(0).getIdReservation();

            reservationDao.deleteReservationById(idReservation);
            Assert.assertNull(reservationDao.findReservationById(idReservation));
        }

    }

    @After
    public void end(){
        userDao.deleteUserById(userId.getIdUser());

    }


}