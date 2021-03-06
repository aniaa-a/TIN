package pl.kosan.tin.dao;

import org.springframework.stereotype.Repository;
import pl.kosan.tin.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    void insertUser(User user);

    void deleteUserById(Long userId);

    User findUserById(Long userId);

    User findUserByMail(String email);

    User findUserByMailAndPass(String email, String password);

    Optional<List<User>> findAllUser();
}
