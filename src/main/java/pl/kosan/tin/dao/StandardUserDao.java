package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.kosan.tin.model.User;

import java.util.List;
import java.util.Optional;

public class StandardUserDao extends NamedParameterJdbcDaoSupport implements UserDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardUserDao.class);

    private final static String INSERT_USER = "INSERT INTO tin_user(name, surname, identity_document, email, phone)" +
            "VALUES(:name, :surname, :identity_document, :email, :phone)";

    @Override
    public void insertUser(User user) {


        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("agreement_text", agreement.getAgreementText())
                .addValue("active", agreement.isActive()).addValue("optional_agr", agreement.getOptionalAgreement())
                .addValue("sorting", agreement.getSorting());
        try {
            getNamedParameterJdbcTemplate().update(INSERT_AGREEMENT, mapSqlParameterSource, keyHolder);
            agreement.setAgreementId((keyHolder.getKey().longValue()));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

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
