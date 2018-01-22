package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.ReservationDao;
import pl.kosan.tin.dao.UserDao;
import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.model.User;

@Service
public class StandardUserService implements UserService {


    @Autowired
    UserDao userDao;

    @Override
    public void registerUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public User findUserById(Long userId) {

        return userDao.findUserById(userId);

    }

    @Override
    public User findUserByMailAndPass(String email, String password) {

        return userDao.findUserByMailAndPass(email, password);
    }

    @Override
    public User findUserByMail(String email) {

        return userDao.findUserByMail(email);
    }


}
