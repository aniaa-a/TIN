package pl.kosan.tin.dao;

import pl.kosan.tin.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    void insertUser(User user);

    void updateUser(User user);

    void deleteUserById(Long userId);

    User findUserById(Long userId);

    Optional<List<User>> findAllUser();
}
