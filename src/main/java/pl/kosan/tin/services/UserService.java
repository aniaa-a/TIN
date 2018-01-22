package pl.kosan.tin.services;

import pl.kosan.tin.model.Reservation;
import pl.kosan.tin.model.User;

public interface UserService {

    void registerUser(User user);

    User findUserById(Long userId);

    User findUserByMailAndPass(String email, String password);

    User findUserByMail(String email);


}
