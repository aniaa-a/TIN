package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.model.User;

import java.util.List;
import java.util.Optional;

public class StandardUserDao extends NamedParameterJdbcDaoSupport implements UserDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardUserDao.class);

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(Long userId) {

    }

    @Override
    public User findUserById(Long userId) {
        return null;
    }

    @Override
    public Optional<List<User>> findAllUser() {
        return Optional.empty();
    }
}
