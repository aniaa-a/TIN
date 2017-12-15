package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.dto.UserReservationRespDto;

import java.util.List;
import java.util.Optional;

public class StandardApplicationDao extends NamedParameterJdbcDaoSupport implements ApplicationDao{

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardApplicationDao.class);


    @Override
    public Optional<List<UserReservationRespDto>> findReservationForUser(String email) {
        return Optional.empty();
    }
}
