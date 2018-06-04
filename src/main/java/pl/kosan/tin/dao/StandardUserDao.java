package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.kosan.tin.model.User;
import pl.kosan.tin.util.Utils;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Repository
public class StandardUserDao extends NamedParameterJdbcDaoSupport implements UserDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardUserDao.class);

    private final static String INSERT_USER = "INSERT INTO tin_user(user_name, user_surname, identity_document, email, phone, password)" +
            "VALUES(:name, :surname, :identity_document, :email, :phone, :password)";
    private final static String FIND_USER_BY_ID = "SELECT id_user, user_name, user_surname, identity_document, email, phone, role from tin_user where id_user = :id_user";
    private final static String FIND_USER_BY_MAIL_AND_PASS = "SELECT id_user, user_name, user_surname, identity_document, email, phone, password, role from tin_user where email = :email AND password = :password";
    private final static String FIND_USER_BY_MAIL = "SELECT id_user, user_name, user_surname, identity_document, email, phone, password, role from tin_user where email = :email";
    private final static String DELETE_USER_BY_ID = "DELETE FROM tin_user where id_user = :id_user";

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void insertUser(User user) {

        String pass = "";
        try {
            pass = Utils.cryptPass(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setPassword(pass);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", user.getName())
                .addValue("surname", user.getSurname()).addValue("identity_document", user.getIdentityNum())
                .addValue("email", user.getEmail()).addValue("phone", user.getPhone())
                .addValue("password", pass);
        try {
            getNamedParameterJdbcTemplate().update(INSERT_USER, mapSqlParameterSource, keyHolder);
            user.setIdUser((keyHolder.getKey().longValue()));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }


    }

    @Override
    public void deleteUserById(Long userId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id_user", userId);

        try {
            getNamedParameterJdbcTemplate().update(DELETE_USER_BY_ID, mapSqlParameterSource);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }


    }

    @Override
    public User findUserById(Long userId) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id_user", userId);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(FIND_USER_BY_ID, mapSqlParameterSource,
                    (rs, rowNum) -> {
                        User user = new User();
                        user.setIdUser(rs.getLong("id_user"));
                        user.setName(rs.getString("user_name"));
                        user.setSurname(rs.getString("user_surname"));
                        user.setIdentityNum(rs.getString("identity_document"));
                        user.setEmail(rs.getString("email"));
                        user.setPhone(rs.getString("phone"));
                        user.setRole(rs.getString("role"));

                        return user;
                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User findUserByMailAndPass(String email, String password) {
        String pass = "";
        try {
            pass = Utils.cryptPass(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        LOGGER.info("po findbymailandpass: "+pass);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email).addValue("password", pass);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(FIND_USER_BY_MAIL_AND_PASS, mapSqlParameterSource,
                    (rs, rowNum) -> {
                        User user = new User();
                        user.setIdUser(rs.getLong("id_user"));
                        user.setName(rs.getString("user_name"));
                        user.setSurname(rs.getString("user_surname"));
                        user.setIdentityNum(rs.getString("identity_document"));
                        user.setEmail(rs.getString("email"));
                        user.setPhone(rs.getString("phone"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(rs.getString("role"));
                        return user;
                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User findUserByMail(String email) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);
        try {
            return getNamedParameterJdbcTemplate().queryForObject(FIND_USER_BY_MAIL, mapSqlParameterSource,
                    (rs, rowNum) -> {
                        User user = new User();
                        user.setIdUser(rs.getLong("id_user"));
                        user.setName(rs.getString("user_name"));
                        user.setSurname(rs.getString("user_surname"));
                        user.setIdentityNum(rs.getString("identity_document"));
                        user.setEmail(rs.getString("email"));
                        user.setPhone(rs.getString("phone"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(rs.getString("role"));
                        return user;
                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<List<User>> findAllUser() {
        return Optional.empty();
    }
}
