package pl.kosan.tin.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import pl.kosan.tin.dto.UserReservationRespDto;

import java.util.List;
import java.util.Optional;

public class StandardApplicationDao extends NamedParameterJdbcDaoSupport implements ApplicationDao{


    @Override
    public Optional<List<UserReservationRespDto>> findReservationForUser(String email) {
        return Optional.empty();
    }
}
